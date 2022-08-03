package ke.bernys.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ke.bernys.backend.models.Product;
import ke.bernys.backend.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return "Product added successfully:" + product.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PutMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id,@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            return "Product updated successfully:" + product.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
