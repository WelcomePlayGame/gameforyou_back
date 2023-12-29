package game.you.controller;


import game.you.dto.ArticleDTORU;
import game.you.entity.ArticleRU;
import game.you.service.ArticleServiceRU;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/article/ru/")
@RequiredArgsConstructor
public class ArticleControllerRU {
    final  private ArticleServiceRU service;
    @GetMapping
    ResponseEntity<List<ArticleDTORU>> getListArticleEN (@RequestParam(required = false) Long id) {
        return ResponseEntity.ok().body(service.getListArticle(id));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ArticleDTORU> getArticleENbyId(@PathVariable("id") String id) {
        try {
            ArticleDTORU article = service.getArticleById(id);
            return ResponseEntity.ok().body(article);
        } catch (EntityNotFoundException e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/add",consumes = "multipart/form-data")
    public ResponseEntity<ArticleDTORU> addArticle(
            @RequestPart (name= "article") ArticleRU articleRU,
            @RequestPart (name= "posterPhoto") List<MultipartFile> posterPhoto,
            @RequestPart(name= "ids") List<Long> ids,
            @RequestPart(name= "tagSet") List<String> tagSet
    ) throws IOException {

        return ResponseEntity.ok().body(service.addArticle(articleRU,posterPhoto , ids, tagSet));

    }
}
