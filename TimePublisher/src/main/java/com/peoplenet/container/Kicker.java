package com.peoplenet.container;

import com.peoplenet.service.app.Poller;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Kicker {

    static {
        System.setProperty("log.name", "TimePublisher");
    }

    public static void main(String[] args) {

        Server server = new Server(7676);

        Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration"
                            , "org.eclipse.jetty.plus.webapp.EnvConfiguration"
                            , "org.eclipse.jetty.plus.webapp.PlusConfiguration");


        WebAppContext webCtx = new WebAppContext();

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ctx.setContextPath("/");
        ctx.addEventListener(new Poller());
        server.setHandler(ctx);
        ServletHolder servHolder = ctx.addServlet(ServletContainer.class, "/*");
        servHolder.setInitOrder(1);
        servHolder.setInitParameter("jersey.config.server.provider.packages","com.peoplenet.ws.v1");

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Kicker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}

