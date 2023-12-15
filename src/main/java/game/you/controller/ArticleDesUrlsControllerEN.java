package game.you.controller;


import game.you.entity.Article_des_urlsEN;
import game.you.entity.Article_des_urlsUA;
import game.you.service.ArticleDesUrlsServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/article_des_url/en")
@RequiredArgsConstructor
public class ArticleDesUrlsControllerEN {
    final private ArticleDesUrlsServiceEN service;
    @GetMapping
    ResponseEntity<List<Article_des_urlsEN>> getListDesUrlEN () {
        return ResponseEntity.ok().body(service.getListUrl());
    }

    @PostMapping("/add")
    ResponseEntity<Article_des_urlsEN> addDesUrlsEN (@RequestPart MultipartFile photo) throws IOException {
        return  ResponseEntity.ok().body(service.addUrl(photo));
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Article_des_urlsEN> deleteUrl (@PathVariable long id) {
        service.deleteUrl(id);
        return  ResponseEntity.noContent().build();
    }
}
