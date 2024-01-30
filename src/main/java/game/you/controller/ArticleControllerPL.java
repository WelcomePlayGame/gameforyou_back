package game.you.controller;

import game.you.dto.ArticleDTOPL;
import game.you.entity.ArticlePL;
import game.you.service.ArticleServicePL;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/article/pl/")
@RequiredArgsConstructor
public class ArticleControllerPL {
    final  private ArticleServicePL service;
    @GetMapping
    ResponseEntity<List<ArticleDTOPL>> getListArticleEN (@RequestParam(required = false) Long id) {
        return ResponseEntity.ok().body(service.getListArticle(id));
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<ArticleDTOPL> getArticleENbyId(@PathVariable("id") String id) {
        try {
            ArticleDTOPL article = service.getArticleById(id);
            return ResponseEntity.ok().body(article);
        } catch (EntityNotFoundException e){
            return  ResponseEntity.notFound().build();
        }
    }
    @PostMapping(value = "/add")
    ResponseEntity<ArticleDTOPL> addArticleEN (
            @RequestPart (name= "article") ArticlePL articlePL,
            @RequestPart (name= "posterPhoto") List<MultipartFile> posterPhoto,
            @RequestPart(name= "ids") List<Long> ids,
            @RequestPart(name= "tagSet") List<String> tagSet


    ) throws IOException {
        return ResponseEntity.ok().body(service.addArticle(articlePL, posterPhoto, ids, tagSet));
    }
    @PutMapping(value = "/update")
    public ResponseEntity<ArticleDTOPL> update(
            @RequestPart (name= "article") ArticlePL articlePL,
            @RequestPart (name= "posterPhoto") List<MultipartFile> posterPhoto,
            @RequestPart(name= "ids") List<Long> ids,
            @RequestPart(name= "tagSet") List<String> tagSet
    ) throws IOException, URISyntaxException {
        return ResponseEntity.ok().body(service.updateArticle(articlePL, posterPhoto, ids, tagSet));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ArticleDTOPL> deleteArticle(@PathVariable("id") long id) {
        service.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}
