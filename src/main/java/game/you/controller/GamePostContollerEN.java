package game.you.controller;

import game.you.dto.GamePostByIdDTOEN;
import game.you.dto.GamePostDTOEN;
import game.you.entity.GamePostEN;
import game.you.service.GamePostServiceEN;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gamepost/en/")
@RequiredArgsConstructor
@Slf4j
public class GamePostContollerEN {

    final private GamePostServiceEN service;
    @GetMapping
    ResponseEntity<List<GamePostDTOEN>> getListGamePostEN (
            @RequestParam(value = "id", required = false) Long id
    ) {
        return  ResponseEntity.ok().body(service.getAllFGame(id));
    }
    @PostMapping(value = "/add")
    public ResponseEntity<GamePostEN> addPostGame (@RequestPart(name = "article") GamePostEN gamePost,
                                                   @RequestPart(name = "posterPhoto") List<MultipartFile> posterPhoto,
                                                   @RequestPart(name = "ids") List<Long> ids,
                                                   @RequestPart(name = "posterPhotoVertical") MultipartFile photo,
                                                   @RequestPart(name = "genresSet") List<String> genresSet,
                                                   @RequestPart(name = "platformsSet") List<String> platformsSet

    ) throws IOException {

        return ResponseEntity.ok().body(service.addGamePost(gamePost, posterPhoto, ids, photo, genresSet, platformsSet));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<GamePostByIdDTOEN> getGamePostDTOEN (@PathVariable(name = "id") String id) {
        try {
             GamePostByIdDTOEN game = service.getGamePostDTOEN(id);
             log.info(game.toString());
             return ResponseEntity.ok().body(game);
        } catch (EntityNotFoundException e){
            return  ResponseEntity.notFound().build();
        }
    }

}
