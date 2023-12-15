package game.you.controller;

import game.you.entity.GamePost_des_urlsUA;
import game.you.service.GamePostDesUrlServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gamepost_des_url/ua")
@RequiredArgsConstructor
public class GamePostDesUrlControllerUA {

    final  private GamePostDesUrlServiceUA service;
    @GetMapping
    ResponseEntity<List<GamePost_des_urlsUA>> getListGamePostUrl() {
        return ResponseEntity.ok().body(service.getListDesurl());
    }

    @PostMapping(value = "/add")
    ResponseEntity<GamePost_des_urlsUA> addGamePostDesUrl(@RequestPart("photo")MultipartFile photo) throws IOException {
    return ResponseEntity.ok().body(service.addGamePostDesUrl(photo));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<GamePost_des_urlsUA> deletePostDesUrl(@PathVariable(name = "id") Long id) {
        service.deleteGamePostDesUrls(id);
        return ResponseEntity.noContent().build();
    }
}
