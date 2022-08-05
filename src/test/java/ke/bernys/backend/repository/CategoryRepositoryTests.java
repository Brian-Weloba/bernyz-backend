package ke.bernys.backend.repository;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ke.bernys.backend.models.Category;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTests {

    private Long categoryId;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void addCategory() {
        Category category = new Category();
        category.setName("Test Category");
        category.setDescription("Test Description");
        categoryRepository.saveCategory(category);

        categoryId = category.getId();
    }

    @Test
    public void testSaveCategorySuccessfully() {
        Category category2 = new Category();
        category2.setName("Test Category");
        category2.setDescription("Test Description");
        categoryRepository.saveCategory(category2);

        Category category = categoryRepository.getCategory(category2.getId());
        assertEquals(category2.getName(), category.getName());
    }

    @Test
    public void testGetCategorySuccessfully() {
        Category category = categoryRepository.getCategory(categoryId);
        assertEquals("Test Category", category.getName());
    }

    @Test
    public void testUpdateCategorySuccessfully() {
        Category category = categoryRepository.getCategory(categoryId);
        category.setName("Vodka");
        categoryRepository.updateCategory( categoryId, category);

        Category category2 = categoryRepository.getCategory(categoryId);
        assertEquals("Vodka", category2.getName());
    }

    @Test
    public void testDeleteCategorySuccessfully() {
        Category category = categoryRepository.getCategory(categoryId);
        categoryRepository.deleteCategory(category.getId());
        try {
            categoryRepository.getCategory(categoryId);
        } catch (Exception e) {
            assertEquals("The category does not exist", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllCategoriesSuccessfully() {
        categoryRepository.deleteAllCategories();
        try {
            categoryRepository.getAllCategories();
        } catch (Exception e) {
            assertEquals("There are no categories in the database", e.getMessage());
        }
    }

    @Test
    public void testGetAllCategoriesSuccessfully() {
        categoryRepository.deleteAllCategories();

        addCategory();

        Category category2 = new Category();
        category2.setName("Test Category 2");
        category2.setDescription("Test Description 2");
        categoryRepository.saveCategory(category2);

        assertEquals(2, categoryRepository.getAllCategories().size());
    }

}
