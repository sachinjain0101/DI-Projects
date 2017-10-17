package com.peoplenet.service.app;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Poller implements ServletContextListener{

    private static final Logger LOGGER = Logger.getLogger( Poller.class.getName() );

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        while(true)
            try {
                LOGGER.debug("i am running but dont know why?");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
