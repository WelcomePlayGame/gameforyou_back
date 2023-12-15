package game.you.repository;

import game.you.entity.GenresRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepositoryRU extends JpaRepository<GenresRU, Long> {
}
