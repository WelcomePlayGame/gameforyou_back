package game.you.controller;

import game.you.dto.ArticleDTORU;
import game.you.dto.ArticleDTOUA;
import game.you.entity.ArticleEN;
import game.you.entity.ArticleUA;
import game.you.service.ArticleServiceUA;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/article/ua/")
@RequiredArgsConstructor
public class ArticleControllerUA {
    final private ArticleServiceUA service;
    @GetMapping
    public ResponseEntity<List<ArticleDTOUA>> getListArticle(
            @RequestParam(required = false) Long id
    ) {
        return ResponseEntity.ok().body(service.getListArticle(id));
    }
    @PostMapping(value = "/add")
    public ResponseEntity<ArticleDTOUA> addArticle(
            @RequestPart (name= "article") ArticleUA articleUA,
            @RequestPart (name= "posterPhoto") List<MultipartFile> posterPhoto,
            @RequestPart(name= "ids") List<Long> ids,
            @RequestPart(name= "tagSet") List<String> tagSet
                                              ) throws IOException {
       for(String tag : tagSet) {
           if (tag == null || tag.isEmpty()) {
               throw new IllegalArgumentException("Illigal tag ["+tag+"]");
           }
       }
        for (MultipartFile file : posterPhoto) {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("Illigal file is ["+file+"]");
            }
        }
        return ResponseEntity.ok().body(service.addArticle(articleUA,posterPhoto , ids, tagSet));

    }
    @PutMapping(value = "/update")
    public ResponseEntity<ArticleDTOUA> updateArticle(
            @RequestPart (name= "article") ArticleUA articleUA,
            @RequestPart (name= "posterPhoto") List<MultipartFile> posterPhoto,
            @RequestPart(name= "ids") List<Long> ids,
            @RequestPart(name= "tagSet") List<String> tagSet
    ) throws IOException {

        return ResponseEntity.ok().body(service.updateArticle(articleUA,posterPhoto , ids, tagSet));

    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ArticleDTOUA> deleteArticle(@PathVariable("id") long id) {
        service.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{id}")
    ResponseEntity<ArticleDTOUA> getArticleENbyId(@PathVariable("id") String id) {
        try {
            ArticleDTOUA article = service.getArticleById(id);
            return ResponseEntity.ok().body(article);
        } catch (EntityNotFoundException e){
            return  ResponseEntity.notFound().build();
        }
    }



}
