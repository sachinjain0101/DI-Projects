package com.peoplenet.service.module;

import org.apache.log4j.Logger;

public class Dummy implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Dummy.class);

    @Override
    public void run() {
        LOGGER.debug("I am a Dummy...");
    }
}
