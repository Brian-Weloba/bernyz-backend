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

import ke.bernys.backend.exception.InvalidArgumentsException;
import ke.bernys.backend.exception.ItemNotFoundException;
import ke.bernys.backend.models.Category;
import ke.bernys.backend.service.CategoryService;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public List<Category> getCategories() {
        try {
            return categoryService.getAllCategories();
        } catch (Exception e) {
            String message = "Error: " + e.getMessage();
            throw new InvalidArgumentsException(message);
        }
    }

    @GetMapping("/{id}")
    public Category getCategory(String id) {
        try {
            return categoryService.getCategory(Long.parseLong(id));
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                String message = "Error: " + e.getMessage();
                throw new InvalidArgumentsException(message);
            } else {
                String message = "Error: " + e.getMessage();
                throw new ItemNotFoundException(message);
            }
        }
    }

    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable String id, @RequestBody Category category) {
        try {
            categoryService.updateCategory(Long.parseLong(id), category);
            return "Category: " + category.getName() + " updated successfully";
        } catch (Exception e) {
            if (e instanceof InvalidArgumentsException) {
                String message = "Error: " + e.getMessage();
                throw new InvalidArgumentsException(message);
            } else {
                String message = "Error: " + e.getMessage();
                throw new ItemNotFoundException(message);
            }
        }
    }

    @PostMapping("/create")
    public String createCategory(@RequestBody Category category) {
        try {
            categoryService.saveCategory(category);
            return "Category: " + category.getName() + " created successfully";
        } catch (Exception e) {
            String message = "Error: " + e.getMessage();
            throw new InvalidArgumentsException(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        try {
            categoryService.deleteCategory(Long.parseLong(id));
            return "Category deleted successfully";
        } catch (Exception e) {
            String message = "Error: " + e.getMessage();
            throw new ItemNotFoundException(message);
        }
    }

    @DeleteMapping("/delete/all")
    public String deleteAllCategories() {
        categoryService.deleteAllCategories();
        return "All categories deleted successfully";
    }

}
