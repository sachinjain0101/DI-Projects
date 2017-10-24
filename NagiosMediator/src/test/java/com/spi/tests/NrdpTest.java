package com.spi.tests;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.shamu.jsendnrdp.domain.State;

import com.spi.services.NrdpService;

public class NrdpTest {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(NrdpTest.class);
	
	public static void main (String[] args) throws UnknownHostException{
	
		String nrdpHost = InetAddress.getLocalHost().getHostName();
		NrdpService.sendNrdpMessage("http://10.100.1.170/nrdp/", nrdpHost, "iosMontiorService","spilocal", 2000, State.OK, "CHALJAA BE");
		
	}
	
	@Test
	public void nrdptest() throws UnknownHostException {
		String nrdpHost = InetAddress.getLocalHost().getHostName();
		NrdpService.sendNrdpMessage("http://10.100.1.170/nrdp/", nrdpHost, "iosMontiorService","sachinjain0101", 60, State.OK, "Device: SPI iPad ## Device Id: 33E32059-E36D-4760-8640-116C84FB73FE ## Status: OK");
	}

}
