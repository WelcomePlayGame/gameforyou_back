package game.you.controller;

import game.you.dto.GamePostByIdDTOEN;
import game.you.dto.GamePostDTOEN;
import game.you.dto.GamePostDTORU;
import game.you.entity.GamePostEN;
import game.you.service.GamePostServiceEN;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/gamepost/en/")
@RequiredArgsConstructor
@Slf4j
public class GamePostContollerEN {

    final private GamePostServiceEN service;
    @GetMapping
    ResponseEntity<List<GamePostDTOEN>> getListGamePostEN (
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "series_games", required = false) String series_games
    ) {
        List<GamePostDTOEN> list = service.getAllFGame(id, series_games);
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES).cachePublic();
        return  ResponseEntity.ok().cacheControl(cacheControl).body(list);
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
    @PutMapping(value = "/update")
    public ResponseEntity<GamePostEN> updateGame(
            @RequestPart(name = "article") GamePostEN gamePost,
            @RequestPart(name = "posterPhoto") List<MultipartFile> posterPhoto,
            @RequestPart(name = "ids") List<Long> ids,
            @RequestPart(name = "posterPhotoVertical") MultipartFile photo,
            @RequestPart(name = "genresSet") List<String> genresSet,
            @RequestPart(name = "platformsSet") List<String> platformsSet
    ) throws URISyntaxException, IOException {
        return ResponseEntity.ok().body(service.updateGame(gamePost, posterPhoto, ids, photo, genresSet, platformsSet));
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
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<ResponseEntity> deleteGame(@PathVariable("id") long id) {
        service.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

}
