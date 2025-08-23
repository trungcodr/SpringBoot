package mini.project.ecomerce_backend.dto;

public class CategoryRequestDTO {
    private String id;
    private String name;

    public CategoryRequestDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryRequestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
