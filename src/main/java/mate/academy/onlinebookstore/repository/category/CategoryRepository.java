package mate.academy.onlinebookstore.repository.category;

import java.util.Optional;
import mate.academy.onlinebookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long id);
}
