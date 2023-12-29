package game.you.repository;

import game.you.entity.ArticleEN;
import game.you.entity.ArticleRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepositoryRU extends JpaRepository<ArticleRU, Long> {
    @Query("select a from ArticleRU a where (:id is null or a.id = :id) order by a.atCreate desc")
    List<ArticleRU> findAllCustom(@Param("id") Long id);
    @Query("select g from ArticleRU g where g.url_post = :id")
    Optional<ArticleRU> findByUrl(@Param("id") String id);
}
