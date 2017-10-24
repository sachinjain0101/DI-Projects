package com.spi.tests;

import org.junit.Test;

import com.spi.services.PushNotificationProvider;

public class PushNotificationTest {

	@Test
	public void testPush() {
		PushNotificationProvider.pushPayLoad ("84fed318eabfedee998d3d0c5e1a9f55f76e8fabd7df55937a14a59140d16318");
	}
	
}
