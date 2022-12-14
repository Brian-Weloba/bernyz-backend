package ke.bernys.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ke.bernys.backend.exception.*;
import ke.bernys.backend.models.Product;
import ke.bernys.backend.service.ProductService;

/**
 * This class is a controller class that handles all the requests that come to
 * the server for the products
 *
 * @author Brian Weloba
 * @version 1.0
 * @since 1.0
 * @see ke.bernys.backend.service.ProductService
 * @see ke.bernys.backend.models.Product
 * @see ke.bernys.backend.exception.ItemNotFoundException
 * @see org.springframework.web.bind.annotation.RestController
 * 
 */
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    /**
     * Injecting the ProductService class into the ProductController class.
     *
     */
    @Autowired
    private ProductService productService;

    /**
     * This function gets all the products from the database
     *
     * @return A list of Product objects.
     */
    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    /**
     * This function gets a product from the database
     *
     * @param id The id of the product to be retrieved.
     * @return A Product object.
     * 
     * @throws ItemNotFoundException If the product is not found.
     *
     */
    @GetMapping("/{id}")
    public Product getProduct(Long id) {
        try {
            return productService.getProduct(id);
        } catch (Exception e) {
            String message = "Error:" + e.getMessage();
            throw new ItemNotFoundException(message);
        }
    }

   /**
    * The function takes a product object as a parameter, saves it to the database, and returns a
    * message
    * 
    * @param product The product object that is to be added to the database.
    * @return A string representation of the product that was added.
    *
    */
    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return "Product added successfully:" + product.toString();
        } catch (Exception e) {
            String message = "Error:" + e.getMessage();
            throw new InvalidArgumentsException(message);
        }
    }

   /**
    * The function updates a product in the database
    * 
    * @param id The id of the product to be updated
    * @param product The product object that is passed in the request body.
    * @return A string representation of the product that was updated.
    */
    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody Product product) {
        try {
            productService.updateProduct(id, product);
            return "Product updated successfully:" + product.toString();
        } catch (Exception e) {
            if (e instanceof ItemNotFoundException) {
                String message = "Error:" + e.getMessage();
                throw new ItemNotFoundException(message);
            } else {
                String message = "Error:" + e.getMessage();
                throw new InvalidArgumentsException(message);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return "Product deleted successfully";
        } catch (Exception e) {
            String message = "Error:" + e.getMessage();
            throw new ItemNotFoundException(message);
        }
    }

    @DeleteMapping("/delete/all")
    public String deleteAllProducts() {
            productService.deleteAllProducts();
            return "All products deleted";
       
    }
}
