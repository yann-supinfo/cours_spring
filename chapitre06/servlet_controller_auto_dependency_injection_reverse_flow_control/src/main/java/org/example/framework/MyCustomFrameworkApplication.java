package org.example.framework;

import jakarta.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import java.util.Map;

public class MyCustomFrameworkApplication {

    private Server server;

    public MyCustomFrameworkApplication() throws Exception {
        DependencyInjector injector = new DependencyInjector();
        injector.initialize("org.example");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Map<Class<?>, String> controllerPaths = injector.getControllerPaths();
        for (Map.Entry<Class<?>, String> entry : controllerPaths.entrySet()) {
            context.addServlet(injector.getServletHolder((Class<? extends HttpServlet>) entry.getKey()), entry.getValue());
        }

        server = new Server(8081);
        server.setHandler(context);
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public static void run(Class<?> applicationClass, String[] args) throws Exception {
        MyCustomFrameworkApplication app = new MyCustomFrameworkApplication();
        app.start();
    }
}