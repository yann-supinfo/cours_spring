package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.controller.ProductController;
import org.example.controller.RestEndpoint;
import org.example.controller.StaticEndpoint;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;

public class App 
{
        public static void main(String[] args) throws Exception {
                ProductRepository productRepository = new ProductRepository();
                ProductService productService = new ProductService(productRepository);
                ProductController productController = new ProductController(productService);
                RestEndpoint restEndpoint = new RestEndpoint();
                StaticEndpoint staticEndpoint = new StaticEndpoint();

                ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
                ServletHolder productServletHolder = new ServletHolder(productController);
                ServletHolder restServletHolder = new ServletHolder(restEndpoint);
                ServletHolder staticServletHolder = new ServletHolder(staticEndpoint);

                context.setContextPath("/");
                context.addServlet(productServletHolder, "/products/*");
                context.addServlet(restServletHolder, "/rest/*");
                context.addServlet(staticServletHolder, "/static/*");

                Server server = new Server(8081);
                server.setHandler(context);

                server.start();
                server.join();
        }
}
