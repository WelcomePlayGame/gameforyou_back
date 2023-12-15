package game.you.controller;

import game.you.entity.Article_des_urlsUA;
import game.you.service.ArticleDesUrlsServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/article_des_url/ua")
@RequiredArgsConstructor
public class ArticleDesUrlsContollerUA {

    final private ArticleDesUrlsServiceUA service;

    @GetMapping
    public ResponseEntity<List<Article_des_urlsUA>> getListUrl () {
        return  ResponseEntity.ok().body(service.getListUrl());
    }
    @PostMapping(value = "/add")
    public ResponseEntity <Article_des_urlsUA> getUrl (@RequestPart MultipartFile photo) throws IOException {
    return  ResponseEntity.ok().body(service.addUrl(photo));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Article_des_urlsUA> deleteUrl (@PathVariable long id) {
        service.deleteUrl(id);
        return  ResponseEntity.noContent().build();
    }
}
