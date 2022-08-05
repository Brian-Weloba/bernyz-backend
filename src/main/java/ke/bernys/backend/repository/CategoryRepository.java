package ke.bernys.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ke.bernys.backend.models.Category;

@Repository
public class CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This method creates a category in the database.
     * 
     * @param category The category to be created.
     * @return Category The category that was created.
     * @throws IllegalArgumentException If the category is null.
     *
     */
    @Transactional
    public void saveCategory(Category category) throws IllegalArgumentException {
        // check if all the fields are filled
        if (category.getName() == null || category.getDescription() == null) {
            throw new IllegalArgumentException("The name and description of the category must be filled");
        }
        entityManager.persist(category);
    }

    /**
     * This method gets a category from the database.
     * 
     * @param id The id of the category to be retrieved.
     * @return Category The category that was retrieved.
     * @throws IllegalArgumentException If the id is null.
     * @throws EntityNotFoundException  If the category does not exist.
     */
    @Transactional
    public Category getCategory(Long id) throws IllegalArgumentException, EntityNotFoundException {
        // check if the id is null
        if (id == null) {
            throw new IllegalArgumentException("The id of the category must be filled");
        }
        // if category does not exist
        else if (entityManager.find(Category.class, id) == null) {
            throw new EntityNotFoundException("The category does not exist");
        } else {
            return entityManager.find(Category.class, id);
        }
    }

    /**
     * This method updates a category in the database.
     * 
     * @param category The category to be updated.
     * @param id       The id of the category to be updated.
     * @return Category The category that was updated.
     * 
     * @throws IllegalArgumentException If the category or id is null.
     * @throws EntityNotFoundException  If the category does not exist.
     *
     */
    @Transactional
    public Category updateCategory(Long id , Category category )
            throws IllegalArgumentException, EntityNotFoundException {
        Category categoryToUpdate = entityManager.find(Category.class, id);
        if (id == null) {
            throw new IllegalArgumentException("The id of the category must be filled");
        } else if (categoryToUpdate == null) {
            throw new EntityNotFoundException("The category does not exist");
        } else {
            categoryToUpdate.setName(category.getName());
            categoryToUpdate.setDescription(category.getDescription());
            entityManager.merge(categoryToUpdate);
            return categoryToUpdate;
        }
    }

    /**
     * This method deletes a category from the database.
     * 
     * @param id The id of the category to be deleted.
     * @return Category The category that was deleted.
     */
    @Transactional
    public void deleteCategory(Long id) {
        Category category = getCategory(id);
        entityManager.remove(category);
    }

    /**
     * This method deletes all categories from the database.
     * 
     */
    @Transactional
    public void deleteAllCategories() {
        entityManager.createQuery("DELETE FROM Category").executeUpdate();
    }

    /**
     * This method gets all categories from the database.
     * 
     * @return List<Category> The list of categories.
     */
    @Transactional
    public List<Category> getAllCategories() throws EntityNotFoundException {
        List<Category> allCategories = entityManager.createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
        if (allCategories.isEmpty()) {
            throw new EntityNotFoundException("There are no categories in the database");
        } else {
            return allCategories;
        }
    }

    /**
     * This method gets all categories from the database.
     * 
     * @param name
     * @return List<Category> The list of categories.
     * @throws IllegalArgumentException If the name is null.
     * @throws EntityNotFoundException If the category does not exist.
     *
     */
    @Transactional
    public List<Category> findCategoryByName(String name) throws IllegalArgumentException, EntityNotFoundException {
        if (name == null) {
            throw new IllegalArgumentException("The name of the category must be filled");
        } else {
            List<Category> category = entityManager
                    .createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                    .setParameter("name", name).getResultList();
            if (category.isEmpty()) {
                throw new EntityNotFoundException("The category does not exist");
            } else {
                return category;
            }

        }

    }
}
