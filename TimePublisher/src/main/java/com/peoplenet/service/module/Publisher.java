package com.peoplenet.service.module;

import org.apache.log4j.Logger;

import java.util.List;

public class Publisher implements Runnable{

    private static final Logger LOGGER = Logger.getLogger( Poller.class.getName() );

    @Override
    public void run() {
        LOGGER.debug("i am publishing...");
        List<String> lstTC = TimeCardOps.getTimeCard(null);
        SimpleProducer.start(lstTC);
    }
}
