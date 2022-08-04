package ke.bernys.backend.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ke.bernys.backend.models.Product;
import java.util.List;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

@SpringBootTest
public class ProductControllerTests {

    private Long productId;

    @Autowired
    private ProductController productController;

    @BeforeEach
    public void addProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImage("Test Image");
        product.setCreated_at(new Timestamp(System.currentTimeMillis()));
        product.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        product.setType("Gin");
        productController.addProduct(product);

        productId = product.getId();
    }

    @Test
    public void testGetAllProductsSuccessfully() {
        List<Product> products = productController.getProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testAddProductSuccessfully() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImage("Test Image");
        product.setCreated_at(new Timestamp(System.currentTimeMillis()));
        product.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        product.setType("Gin");
        productController.addProduct(product);
        List<Product> products = productController.getProducts();
        assertEquals(3, products.size());
    }

    @Test
    public void testGetProductSuccessfully() {
        Product product = productController.getProduct(productId);
        assertEquals("Gin", product.getType());
    }

    @Test
    public void testUpdateProductSuccessfully() {
        Product product = productController.getProduct(productId);
        product.setType("Vodka");
        productController.updateProduct(productId.toString(), product);
        Product product2 = productController.getProduct(productId);
        assertEquals("Vodka", product2.getType());
    }

    // @Test
    // public void test
}
