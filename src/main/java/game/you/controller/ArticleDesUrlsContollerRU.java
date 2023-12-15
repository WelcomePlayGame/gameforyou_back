package game.you.controller;

import game.you.entity.Article_des_urlsRU;
import game.you.service.ArticleDesUrlsServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/article_des_url/ru")
@RequiredArgsConstructor
public class ArticleDesUrlsContollerRU {

    final private ArticleDesUrlsServiceRU service;

    @GetMapping
    public ResponseEntity<List<Article_des_urlsRU>> getListUrl () {
        return  ResponseEntity.ok().body(service.getListUrl());
    }
    @PostMapping(value = "/add")
    public ResponseEntity <Article_des_urlsRU> getUrl (@RequestPart MultipartFile photo) throws IOException {
    return  ResponseEntity.ok().body(service.addUrl(photo));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Article_des_urlsRU> deleteUrl (@PathVariable long id) {
        service.deleteUrl(id);
        return  ResponseEntity.noContent().build();
    }
}
