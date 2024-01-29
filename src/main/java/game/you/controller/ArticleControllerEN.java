package game.you.controller;

import game.you.dto.ArticleDTOEN;
import game.you.dto.ArticleDTOUA;
import game.you.dto.GamePostDTOEN;
import game.you.entity.ArticleEN;
import game.you.entity.ArticleUA;
import game.you.service.ArticleServiceEN;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/article/en/")
@RequiredArgsConstructor
public class ArticleControllerEN {
    final  private ArticleServiceEN service;
    @GetMapping()
    ResponseEntity<List<ArticleDTOEN>> getListArticleEN (
            @RequestParam(required = false) Long id
    ) {
        List<ArticleDTOEN> list = service.getListArticle(id);
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES).cachePublic();
        return ResponseEntity.ok().cacheControl(cacheControl).body(list);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ArticleDTOEN> getArticleENbyId(@PathVariable("id") String id) {
        try {
            ArticleDTOEN article = service.getArticleById(id);
            return ResponseEntity.ok().body(article);
        } catch (EntityNotFoundException e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/add")
    ResponseEntity<ArticleDTOEN> addArticleEN (
            @RequestPart (name= "article") ArticleEN articleEN,
            @RequestPart (name= "posterPhoto", required = false) List<MultipartFile> posterPhoto,
            @RequestPart(name= "ids") List<Long> ids,
            @RequestPart(name= "tagSet") List<String> tagSet


    ) throws IOException {
        if (articleEN.getGamePost() == null) {
            System.out.println("GamePost is " + null);
        } else {
            System.out.println("not null");
        }
        return ResponseEntity.ok().body(service.addArticle(articleEN, posterPhoto, ids, tagSet));
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ArticleDTOEN> updateArticle(
            @RequestPart (name= "article") ArticleEN articleEN,
            @RequestPart (name= "posterPhoto") List<MultipartFile> posterPhoto,
            @RequestPart(name= "ids") List<Long> ids,
            @RequestPart(name= "tagSet") List<String> tagSet
    ) throws IOException, URISyntaxException {

        return ResponseEntity.ok().body(service.updateArticle(articleEN,posterPhoto , ids, tagSet));

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ArticleDTOEN> deleteArticle(@PathVariable("id") long id) {
        service.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }


}
