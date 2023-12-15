package game.you.controller;

import game.you.dto.GamePostDTOUA;
import game.you.entity.GamePostUA;
import game.you.service.GamePostServiceUA;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gamepost/ua/")
@RequiredArgsConstructor
public class GamePostControllerUA {

    final private GamePostServiceUA service;

    @GetMapping
    public ResponseEntity<List<GamePostDTOUA>> getAllGame() {
        return  ResponseEntity.ok().body(service.getAllFGame());
    }


    @PostMapping(value = "/add")
    public ResponseEntity<GamePostUA> addPostGame (@RequestPart(name = "article") GamePostUA gamePost,
                                                   @RequestPart(name = "posterPhoto") List<MultipartFile> posterPhoto,
                                                   @RequestPart(name = "ids") List<Long> ids,
                                                   @RequestPart(name = "posterPhotoVertical") MultipartFile photo,
                                                   @RequestPart(name = "genresSet") List<String> genresSet,
                                                   @RequestPart(name = "platformsSet") List<String> platformsSet

    ) throws IOException {

        return ResponseEntity.ok().body(service.addGamePost(gamePost, posterPhoto, ids, photo, genresSet, platformsSet));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<GamePostDTOUA> getPostById (@PathVariable("id") String id) {
        try {
            GamePostDTOUA gamePostDTOUA = service.getPostById(id);
            return ResponseEntity.ok().body(gamePostDTOUA);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
