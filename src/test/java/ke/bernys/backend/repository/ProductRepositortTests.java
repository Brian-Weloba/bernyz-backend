package ke.bernys.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ke.bernys.backend.models.Product;

// @RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositortTests {

    private Long productId;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void addProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImage("Test Image");
        product.setCreated_at(new Timestamp(System.currentTimeMillis()));
        product.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        product.setType("Gin");
        productRepository.saveProduct(product);

        productId = product.getId();
    }

    @Test
    public void testSaveProductSuccessfully() {
        Product product2 = new Product();
        product2.setName("Test Product2");
        product2.setDescription("Test Description2");
        product2.setImage("Test Image2");
        product2.setCreated_at(new Timestamp(System.currentTimeMillis()));
        product2.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        product2.setType("Vodka");
        productRepository.saveProduct(product2);

        Product product = productRepository.getProduct(product2.getId());
        assertEquals(product2.getName(), product.getName());
    }

    @Test
    public void testGetProductSuccessfully() {
        Product product = productRepository.getProduct(productId);
        assertEquals("Gin", product.getType());
    }

    @Test
    public void testUpdateProductSuccessfully() {
        Product product = productRepository.getProduct(productId);
        product.setType("Vodka");
        productRepository.updateProduct(productId.toString(), product);

        Product product2 = productRepository.getProduct(productId);
        assertEquals("Vodka", product2.getType());
    }

    @Test
    public void testDeleteProductSuccessfully() {
        Product product = productRepository.getProduct(productId);
        productRepository.deleteProduct(product.getId());
        try {
            productRepository.getProduct(productId);
        } catch (Exception e) {
            assertEquals("The product does not exist", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllProductsSuccessfully() {
        productRepository.deleteAllProducts();
        assertEquals(0, productRepository.getAllProducts().size());
    }

    @Test
    public void testGetAllProductsSuccessfully() {
        productRepository.deleteAllProducts();

        addProduct();

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
