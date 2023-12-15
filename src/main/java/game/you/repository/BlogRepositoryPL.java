package game.you.repository;

import game.you.entity.BlogEN;
import game.you.entity.BlogPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepositoryPL extends JpaRepository<BlogPL, Long> {
}
