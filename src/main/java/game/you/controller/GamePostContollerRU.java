package game.you.controller;

import game.you.dto.GamePostDTORU;
import game.you.entity.GamePostEN;
import game.you.entity.GamePostRU;
import game.you.service.GamePostServiceRU;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gamepost/ru/")
@RequiredArgsConstructor
public class GamePostContollerRU {

    final private GamePostServiceRU service;
    @GetMapping
    ResponseEntity<List<GamePostDTORU>> getListGamePostRU () {
        return  ResponseEntity.ok().body(service.getAllFGame());
    }
    @PostMapping(value = "/add")
    public ResponseEntity<GamePostDTORU> addPostGame (@RequestPart(name = "article") GamePostRU gamePost,
                                                   @RequestPart(name = "posterPhoto") List<MultipartFile> posterPhoto,
                                                   @RequestPart(name = "ids") List<Long> ids,
                                                   @RequestPart(name = "posterPhotoVertical") MultipartFile photo,
                                                   @RequestPart(name = "genresSet") List<String> genresSet,
                                                   @RequestPart(name = "platformsSet") List<String> platformsSet

    ) throws IOException {

        return ResponseEntity.ok().body(service.addGamePost(gamePost, posterPhoto, ids, photo, genresSet, platformsSet));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<GamePostDTORU> getPostById (@PathVariable("id") String id) {
        try {
            GamePostDTORU gamePostDTORU = service.getGameById(id);
                return ResponseEntity.ok().body(gamePostDTORU);
            } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
