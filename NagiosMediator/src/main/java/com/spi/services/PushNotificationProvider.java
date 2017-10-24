package com.spi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;

public class PushNotificationProvider {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PushNotificationProvider.class);

	public static void pushPayLoad(String deviceToken) {
		LOGGER.info("Inside pushPaylod for - {}", deviceToken);
		try {
			ApnsService service = APNS
					.newService()
					.withCert(
							PushNotificationProvider.class.getClass().getResourceAsStream(
									"/security/Certificates.p12"), "MamathaSpi123")
					.withProductionDestination().build();
			// for development environment
			// .withSandboxDestination().build();

			service.start();
			PayloadBuilder payloadBuilder = APNS.newPayload();
			payloadBuilder.alertBody("Monitoring agent is not running !!!");
			payloadBuilder.sound("default");

			String payload = payloadBuilder.build();
			String token = deviceToken; // "9251aaab1167ecd8318f43d414863c2c9c06e2d2a7ef53f150f24353e50ebab6";//
			service.push(token, payload);
			service.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("pushPayLoad done for - {}", deviceToken);
	}
}
