package game.you.repository;

import game.you.entity.CategoryEN;
import game.you.entity.CategoryUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryEN extends JpaRepository<CategoryEN, Long> {
}
