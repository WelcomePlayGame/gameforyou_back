package game.you.repository;

import game.you.entity.GenresUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepositotyUA extends JpaRepository<GenresUA, Long> {
}