package ke.bernys.backend.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Brian Weloba
 * @version 1.0
 * @since 1.0
 * 
 *        This class represents a product in the database
 */
@Entity
@Table(name = "products")
public class Product {
    /**
     * The id of the product.
     * 
     * This is the primary key of the product table.
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * The name of the product.
     */
    private String name;
    /**
     * The description of the product.
     */
    private String description;
    /**
     * This represents the location of the image of the product.
     * This is a relative path to the image of the product.
     * This is not stored in the database.
     */
    private String image;
    /**
     * The timestamp of when the product was created.
     */
    private Timestamp created_at;
    /**
     * The timestamp of when the product was last updated.
     */
    private Timestamp updated_at;
    /**
     * Whether or not the product is in stock.
     * This is not stored in the database.
     */
    @Transient
    private boolean in_stock;
    /**
     * The type of product eg. gin, rum, vodka, etc.
     */
    private String type;
    /**
     * The categories that the product belongs to.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Product() {
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public Product(String name, String description, String image, Timestamp updated_at,
             String type) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
        this.type = type;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return Timestamp return the created_at
     */
    public Timestamp getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    /**
     * @return Timestamp return the updated_at
     */
    public Timestamp getUpdated_at() {
        return updated_at;
    }

    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    /**
     * @return boolean return the in_stock
     */
    public boolean isIn_stock() {
        return in_stock;
    }

    /**
     * @param in_stock the in_stock to set
     */
    public void setIn_stock(boolean in_stock) {
        this.in_stock = in_stock;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return List<Category> return the categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    
    /**
     * The toString() method returns a string representation of the object
     * 
     * @return The toString() method is being returned.
     */
    @Override
    public String toString() {
        return "Product [categories=" + categories + ", created_at=" + created_at + ", description=" + description
                + ", id=" + id + ", image=" + image + ", in_stock=" + in_stock + ", name=" + name + ", type=" + type
                + ", updated_at=" + updated_at + "]";
    }

}
