package ke.bernys.backend.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ke.bernys.backend.models.Product;

@SpringBootTest
public class ProductControllerTests {

    private Long productId;

    @Autowired
    private  ProductController productController;

  

    @BeforeEach
    public void addProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImage("Test Image");
        product.setType("Gin");
        productController.addProduct(product);

        productId = product.getId();
    }

    // @AfterAll
    // public static void clearProducts() {
    //     productController.deleteAllProducts();
    // }

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
        product.setType("Gin");
        productController.addProduct(product);
        List<Product> products = productController.getProducts();
        assertEquals(2, products.size());
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
}
