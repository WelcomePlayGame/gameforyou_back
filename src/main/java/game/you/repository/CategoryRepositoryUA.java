package game.you.repository;

import game.you.entity.CategoryUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryUA extends JpaRepository<CategoryUA, Long> {
}
