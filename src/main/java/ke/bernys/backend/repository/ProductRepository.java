package ke.bernys.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ke.bernys.backend.models.Product;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This method creates a product in the database.
     * 
     * @param product The product to be created.
     * @return Product The product that was created.
     * @throws IllegalArgumentException If the product is null.
     *
     */
    /**
     * If the name and description of the product are not null, then persist the product
     * 
     * @param product the product to be saved
     */
    @Transactional
    public void saveProduct(Product product) throws IllegalArgumentException {
        // check if all the fields are filled
        if (product.getName() == null || product.getDescription() == null) {
            throw new IllegalArgumentException("The name and description of the product must be filled");
        }
        entityManager.persist(product);
    }

    /**
     * This method gets a product from the database.
     * 
     * @param id The id of the product to be retrieved.
     * @return Product The product that was retrieved.
     * @throws IllegalArgumentException If the id is null.
     * @throws EntityNotFoundException  If the product does not exist.
     */
    @Transactional
    public Product getProduct(Long id) throws IllegalArgumentException, EntityNotFoundException {
        // check if the id is null
        if (id == null) {
            throw new IllegalArgumentException("The id of the product must be filled");
        }
        // if product does not exist
        else if (entityManager.find(Product.class, id) == null) {
            throw new EntityNotFoundException("The product does not exist");
        } else {
            return entityManager.find(Product.class, id);
        }
    }

    /**
     * This method updates a product in the database.
     * 
     * @param product The product to be updated.
     * @return Product The product that was updated.
     * @throws IllegalArgumentException If the product is null.
     * @throws EntityNotFoundException  If the product does not exist.
     */
    @Transactional
    public Product updateProduct(Product product) throws IllegalArgumentException, EntityNotFoundException {
        // check if the product is null
        if (product == null) {
            throw new IllegalArgumentException("The product must be filled");
        }
        // check if the product exists
        else if (entityManager.find(Product.class, product.getId()) == null) {
            throw new EntityNotFoundException("The product does not exist");
        } else {
            entityManager.merge(product);
            return product;
        }
    }

    /**
     * This method deletes a product from the database.
     * 
     * @param id The id of the product to be deleted.
     * @throws IllegalArgumentException If the id is null.
     * @throws EntityNotFoundException  If the product does not exist.
     */
    @Transactional
    public void deleteProduct(Long id) throws IllegalArgumentException, EntityNotFoundException {
        // check if the id is null
        if (id == null) {
            throw new IllegalArgumentException("The id of the product must be filled");
        }
        // check if the product exists
        else if (entityManager.find(Product.class, id) == null) {
            throw new EntityNotFoundException("The product does not exist");
        } else {
            entityManager.remove(entityManager.find(Product.class, id));
        }
    }

    /**
     * Delete all products from the database.
     */
    @Transactional
    public void deleteAllProducts() {
        entityManager.createQuery("DELETE FROM Product").executeUpdate();
    }

   /**
    * Get all products from the database and return them as a list of Product objects.
    * 
    * @return A list of all products.
    */
    @Transactional
    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
