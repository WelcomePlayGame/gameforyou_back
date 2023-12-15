package game.you.repository;

import game.you.entity.ArticlePL;
import game.you.entity.ArticleUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepositoryPL extends JpaRepository<ArticlePL, Long> {
    @Query("select  g from ArticlePL g where g.url_post = :id")
    Optional<ArticlePL> findByUrl(@Param("id") String id);

}
