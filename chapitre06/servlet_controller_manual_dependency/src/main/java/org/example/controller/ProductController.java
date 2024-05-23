package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import org.example.dto.Product;
import org.example.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductController extends HttpServlet {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Product> products = productService.getAllProducts();
            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(products));
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Product product = productService.getProductById(id);
                if (product == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    resp.setContentType("application/json");
                    resp.getWriter().write(objectMapper.writeValueAsString(product));
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = objectMapper.readValue(req.getInputStream(), Product.class);
        Product createdProduct = productService.addProduct(product);
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(createdProduct));
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = objectMapper.readValue(req.getInputStream(), Product.class);
        Product updatedProduct = productService.updateProduct(product);
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(updatedProduct));
    }
}