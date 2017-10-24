package com.peoplenet.service.module;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Poller implements ServletContextListener{

    private static final Logger LOGGER = Logger.getLogger( Poller.class.getName() );

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("Poller Initialized...");
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Publisher(), 0, 5000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
