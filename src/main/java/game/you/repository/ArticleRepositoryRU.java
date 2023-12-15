package game.you.repository;

import game.you.entity.ArticleEN;
import game.you.entity.ArticleRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepositoryRU extends JpaRepository<ArticleRU, Long> {
    @Query("select g from ArticleRU g where g.url_post = : id")
    Optional<ArticleRU> findByUrl(@Param("id") String id);
}
