package game.you.repository;

import game.you.entity.ArticleEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepositoryEN extends JpaRepository<ArticleEN, Long> {
    @Query("select a from ArticleEN a where (:id is null or a.id = :id) order by a.atCreate desc")
    List<ArticleEN> findAllCustom(@Param("id") Long id);
    @Query("select  g from  ArticleEN  g where g.url_post = :id")
    Optional<ArticleEN> findByUrl(@Param("id") String id);
}
