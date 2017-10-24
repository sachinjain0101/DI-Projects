package com.peoplenet.container;

import com.peoplenet.service.module.Poller;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kicker {

    static {
        System.setProperty("log.name", "TimePublisher");
    }

    public static void main(String[] args) {


        Server server = new Server(6661);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addEventListener(new Poller());
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder holder = context.addServlet(ServletContainer.class.getCanonicalName(), "/*");
        holder.setInitOrder(0);
        Map<String, String> initMap = new HashMap<String, String>();
        initMap.put("jersey.config.server.provider.packages", "com.peoplenet.controller.v1; org.codehaus.jackson.jaxrs");
        initMap.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        holder.setInitParameters(initMap);

        try {
            server.setStopAtShutdown(true);
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Kicker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}

