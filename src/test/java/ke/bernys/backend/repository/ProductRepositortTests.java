package ke.bernys.backend.repository;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ke.bernys.backend.models.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositortTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImage("Test Image");
        product.setCreated_at(new Timestamp(System.currentTimeMillis()));
        product.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        product.setType("Gin");
        productRepository.saveProduct(product);

        Product product2 = productRepository.getProduct(product.getId());
        assertEquals(product.getName(), product2.getName());
    }

    @Test
    public void testGetProduct() {
        Product product = productRepository.getProduct(1L);
        assertEquals("Gin", product.getType());
    }

    @Test
    public void testUpdateProduct() {
        Product product = productRepository.getProduct(1L);
        product.setType("Vodka");
        productRepository.updateProduct(product);

        Product product2 = productRepository.getProduct(1L);
        assertEquals("Vodka", product2.getType());
    }

    @Test
    public void testDeleteProduct() {
        Product product = productRepository.getProduct(1L);
        productRepository.deleteProduct(product.getId());
        try {
            productRepository.getProduct(1L);
        } catch (Exception e) {
            assertEquals("The product does not exist", e.getMessage());
        }
    }

    @Test
    public void testGetAllProducts() {
        // save 2 products
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImage("Test Image");
        product.setCreated_at(new Timestamp(System.currentTimeMillis()));
        product.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        product.setType("Gin");
        productRepository.saveProduct(product);

        Product product2 = new Product();
        product2.setName("Test Product 2");
        product2.setDescription("Test Description 2");
        product2.setImage("Test Image 2");
        product2.setCreated_at(new Timestamp(System.currentTimeMillis()));
        product2.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        product2.setType("Vodka");
        productRepository.saveProduct(product2);

        assertEquals(2, productRepository.getAllProducts().size());
    }

}
