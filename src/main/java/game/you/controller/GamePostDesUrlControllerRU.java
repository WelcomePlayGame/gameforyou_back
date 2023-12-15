package game.you.controller;

import game.you.entity.GamePost_des_urlsRU;
import game.you.service.GamePostDesUrlServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gamepost_des_url/ru")
@RequiredArgsConstructor
public class GamePostDesUrlControllerRU {
    final  private GamePostDesUrlServiceRU service;

    @GetMapping
    ResponseEntity<List<GamePost_des_urlsRU>> getListGamePostUrls () {
        return  ResponseEntity.ok().body(service.getListDesurl());
    }

    @PostMapping(value = "/add")
    ResponseEntity<GamePost_des_urlsRU> addPostDesUrl(@RequestPart("photo") MultipartFile photo) throws IOException {
        return ResponseEntity.ok().body(service.addGamePostDesUrl(photo));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<GamePost_des_urlsRU> deletePostDesUrl (@PathVariable("id") long id) {
        service.deleteGamePostDesUrls(id);
        return ResponseEntity.noContent().build();
    }
}
