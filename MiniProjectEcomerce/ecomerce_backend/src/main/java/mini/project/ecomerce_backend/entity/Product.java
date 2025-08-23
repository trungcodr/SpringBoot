package mini.project.ecomerce_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table (name = "products")
public class Product {
    @Id
    private String id;
    private String categoryId;
    private String name;
    private BigDecimal price;
    private int stock;
    private String thumbnail;
    private String shortDesc;
    private String description;

    public Product() {
    }
    public Product(String id, String categoryId, String name, BigDecimal price, int stock, String thumbnail, String shortDesc, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.thumbnail = thumbnail;
        this.shortDesc = shortDesc;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
