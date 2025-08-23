package mini.project.ecomerce_backend.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productId;
    private String imageUrl;

    public ProductImage() {
    }
    public ProductImage(int id, String productId, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
