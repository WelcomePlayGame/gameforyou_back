package game.you.repository;

import game.you.entity.ArticleUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepositoryUA extends JpaRepository<ArticleUA, Long> {
    @Query("select g from  ArticleUA g where g.url_post = :id")
    Optional<ArticleUA> findByUrl(@Param("id") String id);
}
