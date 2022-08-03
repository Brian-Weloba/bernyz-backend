package ke.bernys.backend.models;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
