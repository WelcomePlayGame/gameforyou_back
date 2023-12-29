package game.you.repository;

import game.you.entity.ArticleEN;
import game.you.entity.ArticlePL;
import game.you.entity.ArticleUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepositoryPL extends JpaRepository<ArticlePL, Long> {
    @Query("select a from ArticlePL a where (:id is null or a.id = :id) order by a.atCreate desc")
    List<ArticlePL> findAllCustom(@Param("id") Long id);
    @Query("select  g from ArticlePL g where g.url_post = :id")
    Optional<ArticlePL> findByUrl(@Param("id") String id);

}
