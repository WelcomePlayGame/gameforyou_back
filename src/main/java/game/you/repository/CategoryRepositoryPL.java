package game.you.repository;

import game.you.entity.CategoryEN;
import game.you.entity.CategoryPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryPL extends JpaRepository<CategoryPL, Long> {
}
