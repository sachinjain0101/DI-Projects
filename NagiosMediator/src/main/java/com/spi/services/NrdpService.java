package com.spi.services;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.shamu.jsendnrdp.NRDPServerConnectionSettings;
import ch.shamu.jsendnrdp.NagiosCheckSender;
import ch.shamu.jsendnrdp.domain.NagiosCheckResult;
import ch.shamu.jsendnrdp.domain.State;
import ch.shamu.jsendnrdp.impl.NonBlockingNagiosCheckSender;

import com.google.common.collect.Lists;

public class NrdpService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(NrdpService.class);

	public static void sendNrdpMessage(String nrdpUrl, String nrdpHost, String nrdpService, String nrdpToken, int nrdpTimeout, State nrdpState,
			String nrdpMsg) {

		NRDPServerConnectionSettings nrdpConnectionSettings = new NRDPServerConnectionSettings(nrdpUrl, nrdpToken, nrdpTimeout);
		NagiosCheckSender resultSender = new NonBlockingNagiosCheckSender(nrdpConnectionSettings, 1, 1);

		NagiosCheckResult resultToSend = new NagiosCheckResult(nrdpHost, nrdpService, nrdpState, nrdpMsg);
		Collection<NagiosCheckResult> resultsToSend = Lists.newArrayList();
		resultsToSend.add(resultToSend);

		ExceptionRetryService retry = new ExceptionRetryService();
		while (retry.shouldRetry()) {
			try {
				resultSender.send(resultsToSend);
				break;
			} catch (Exception e) {
				try {
					LOGGER.info(">>> Number of tries left {}",retry.numberOfTriesLeft);
					retry.errorOccured();
				} catch (RuntimeException re) {
					LOGGER.info("Error sending check result to nagios: {}: {}", re.getClass().getCanonicalName());
				} catch (Exception ex) {
					LOGGER.info("Error sending check result to nagios: {}: {}", ex.getClass().getCanonicalName());
				}
			}
		}

	}

	static class ExceptionRetryService {
		public static final int DEFAULT_RETRIES = 2;
		public static final long DEFAULT_WAIT_TIME_IN_MILLI = 5000;

		private int numberOfRetries;
		private int numberOfTriesLeft;
		private long timeToWait;

		public ExceptionRetryService() {
			this(DEFAULT_RETRIES, DEFAULT_WAIT_TIME_IN_MILLI);
		}

		public ExceptionRetryService(int numberOfRetries, long timeToWait) {
			this.numberOfRetries = numberOfRetries;
			numberOfTriesLeft = numberOfRetries;
			this.timeToWait = timeToWait;
		}

		/**
		 * @return true if there are tries left
		 */
		public boolean shouldRetry() {
			return numberOfTriesLeft > 0;
		}

		public void errorOccured() throws Exception {
			numberOfTriesLeft--;
			if (!shouldRetry()) {
				throw new Exception("Retry Failed: Total " + numberOfRetries + " attempts made at interval " + getTimeToWait() + "ms");
			}
			waitUntilNextTry();
		}

		public long getTimeToWait() {
			return timeToWait;
		}

		private void waitUntilNextTry() {
			try {
				Thread.sleep(getTimeToWait());
			} catch (InterruptedException ignored) {}
		}
	}

}
