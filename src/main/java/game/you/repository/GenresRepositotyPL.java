package game.you.repository;

import game.you.entity.GenresEN;
import game.you.entity.GenresPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepositotyPL extends JpaRepository<GenresPL, Long> {
}
