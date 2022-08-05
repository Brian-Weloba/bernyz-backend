package ke.bernys.backend.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ke.bernys.backend.models.Category;

@SpringBootTest
public class CategoryControllerTests {
    
    private Long categoryId;

    @Autowired
    private CategoryController categoryController;


    @BeforeEach
    public void addCategory() {
        Category category = new Category();
        category.setName("Test Category");
        category.setDescription("Test Description");
        categoryController.createCategory(category);

        categoryId = category.getId();
    }

    @AfterEach
    public void clearCategorys() {
        categoryController.deleteAllCategories();
    }

    @Test
    public void testGetAllCategoriesSuccessfully() {
        addCategory();
        List<Category> categories = categoryController.getCategories();
        assertEquals(2, categories.size());
    }

    @Test
    public void testAddCategorySuccessfully() {
        addCategory();
        List<Category> categories = categoryController.getCategories();
        assertEquals(2, categories.size());
    }

    @Test
    public void testGetCategorySuccessfully() {
        Category category = categoryController.getCategory(categoryId.toString());
        assertEquals("Test Category", category.getName());
    }

    @Test
    public void testUpdateCategorySuccessfully() {
        Category category = categoryController.getCategory(categoryId.toString());
        category.setName("Vodka");
        categoryController.updateCategory(categoryId.toString(), category);

        Category category2 = categoryController.getCategory(categoryId.toString());
        assertEquals("Vodka", category2.getName());
    }

    @Test
    public void testDeletCategorySuccessfully() {
        categoryController.deleteCategory(categoryId.toString());
        try {
            categoryController.getCategory(categoryId.toString());
        } catch (Exception e) {
            assertEquals("Error: The category does not exist", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllCategoriesSuccessfully() {
        categoryController.deleteAllCategories();
        try {
            categoryController.getCategories();
        } catch (Exception e) {
            assertEquals("Error: There are no categories in the database", e.getMessage());
        }
    }
}
