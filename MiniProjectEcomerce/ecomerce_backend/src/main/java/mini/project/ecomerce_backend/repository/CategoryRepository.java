package mini.project.ecomerce_backend.repository;

import mini.project.ecomerce_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByDeletedFalse();
}
