package com.peoplenet.main;

import com.peoplenet.ws.v1.RestTest;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kicker {
    public static void main(String[] args) {
        Server server = new Server(7676);

        ServletContextHandler ctx =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/rest/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages",
                "com.peoplenet.ws.v1");

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

