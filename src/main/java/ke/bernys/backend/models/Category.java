package ke.bernys.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;


/**
 * @author Brian Weloba
 * @version 1.0
 * @since 1.0
 * 
 *       This class represents a category in the database
 *
 */
@Entity
public class Category {
    /**
     * The id of the category.
     * 
     * This is the primary key of the category table.
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * The name of the category.
     */
    private String name;
    /**
     * The description of the category.
     */
    private String description;
    /**
     * The products in the category.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "categories_products",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    

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
     * @return List<Product> return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * This returns a string representation of the category.
     * 
     * @return A string representation of the category.
     * @see java.lang.Object#toString()
     *
     */
    @Override
    public String toString() {
        return "Category [description=" + description + ", id=" + id + ", name=" + name + ", products=" + products + "]";
    }

    /**
     * This is an equals method for the category.
     * 
     * @param obj The object to compare to.
     * @return Whether or not the objects are equal.
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (products == null) {
            if (other.products != null)
                return false;
        } else if (!products.equals(other.products))
            return false;
        return true;
    }

    /**
     * This returns a hash code for the category.
     * 
     * @return A hash code for the category.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((products == null) ? 0 : products.hashCode());
        return result;
    }


}
