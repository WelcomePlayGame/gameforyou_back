package game.you.controller;

import game.you.entity.GamePost_des_urlsEN;
import game.you.service.GamePostDesUrlServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gamepost_des_url/en")
@RequiredArgsConstructor
public class GamePostDesUrlControllerEN {
    final  private GamePostDesUrlServiceEN service;

    @GetMapping
    ResponseEntity<List<GamePost_des_urlsEN>> getListGamePostUrls () {
        return  ResponseEntity.ok().body(service.getListDesurl());
    }

    @PostMapping(value = "/add")
    ResponseEntity<GamePost_des_urlsEN> addPostDesUrl (@RequestPart("photo") MultipartFile photo) throws IOException {
        return  ResponseEntity.ok().body(service.addGamePostDesUrl(photo));
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<GamePost_des_urlsEN> deletePostDesUrl(@PathVariable("id") long id) {
        service.deleteGamePostDesUrls(id);
        return ResponseEntity.noContent().build();
    }
}
