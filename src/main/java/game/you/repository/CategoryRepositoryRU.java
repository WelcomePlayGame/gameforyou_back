package game.you.repository;

import game.you.entity.CategoryEN;
import game.you.entity.CategoryRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryRU extends JpaRepository<CategoryRU, Long> {
}
