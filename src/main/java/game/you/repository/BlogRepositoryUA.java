package game.you.repository;

import game.you.entity.BlogUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepositoryUA extends JpaRepository<BlogUA, Long> {
}
