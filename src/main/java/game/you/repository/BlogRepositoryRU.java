package game.you.repository;

import game.you.entity.BlogEN;
import game.you.entity.BlogRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepositoryRU extends JpaRepository<BlogRU, Long> {
}
