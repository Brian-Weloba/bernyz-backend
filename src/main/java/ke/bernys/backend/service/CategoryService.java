package ke.bernys.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ke.bernys.backend.models.Category;
import ke.bernys.backend.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.saveCategory(category);
    }

    public Category getCategory(Long id) {
        return categoryRepository.getCategory(id);
    }

    public void updateCategory(Long id, Category category) {
        categoryRepository.updateCategory(id, category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteCategory(id);
    }

    public void deleteAllCategories() {
        categoryRepository.deleteAllCategories();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public List<Category> findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

}
