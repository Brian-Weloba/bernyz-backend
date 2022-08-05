package ke.bernys.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ke.bernys.backend.models.Product;
import ke.bernys.backend.repository.ProductRepository;

/**
 * It's a service class that uses the ProductRepository class to save and retrieve products
 */
@Service
public class ProductService {

   // Injecting the ProductRepository class into the ProductService class.
    @Autowired
    private ProductRepository productRepository;

    /**
     * It saves a product.
     * 
     * @param product The product object that is to be saved.
     */
    public void saveProduct(Product product) {
        productRepository.saveProduct(product);
    }

    /**
     * This function gets a product from the database
     * 
     * @param id The id of the product to be retrieved.
     * @return A Product object.
     */
    public Product getProduct(Long id) {

            return productRepository.getProduct(id);

    }

    /**
     * This function updates a product in the database
     * 
     * @param product The product object that you want to update.
     * @return The product that was updated.
     */
    public Product updateProduct(String id , Product product) {
        return productRepository.updateProduct(id,product);
    }

   /**
    * It deletes a product from the database
    * 
    * @param id The id of the product to be deleted.
    */
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    /**
     * It deletes all products
     */
    public void deleteAllProducts() {
        productRepository.deleteAllProducts();
    }

   /**
    * It returns a list of products from the product repository
    * 
    * @return A list of products.
    */
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }  
}
