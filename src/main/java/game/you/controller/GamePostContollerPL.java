package game.you.controller;

import game.you.dto.GamePostDTOEN;
import game.you.dto.GamePostDTOPL;
import game.you.entity.GamePostEN;
import game.you.entity.GamePostPL;
import game.you.service.GamePostServiceEN;
import game.you.service.GamePostServicePL;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gamepost/pl/")
@RequiredArgsConstructor
public class GamePostContollerPL {

    final private GamePostServicePL service;
    @GetMapping
    ResponseEntity<List<GamePostDTOPL>> getListGamePostEN () {
        return  ResponseEntity.ok().body(service.getAllFGame());
    }
    @PostMapping(value = "/add")
    public ResponseEntity<GamePostPL> addPostGame (@RequestPart(name = "article") GamePostPL gamePost,
                                                   @RequestPart(name = "posterPhoto") List<MultipartFile> posterPhoto,
                                                   @RequestPart(name = "ids") List<Long> ids,
                                                   @RequestPart(name = "posterPhotoVertical") MultipartFile photo,
                                                   @RequestPart(name = "genresSet") List<String> genresSet,
                                                   @RequestPart(name = "platformsSet") List<String> platformsSet

    ) throws IOException {
        return ResponseEntity.ok().body(service.addGamePost(gamePost, posterPhoto, ids, photo, genresSet, platformsSet));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<GamePostDTOPL> getGamePostbyId (@PathVariable(name = "id") String  id) {
        try {
            GamePostDTOPL game = service.getGamebyId(id);
            return ResponseEntity.ok().body(game);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
