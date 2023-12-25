package game.you.controller;


import game.you.dto.GenresDTOUA;
import game.you.entity.GenresUA;
import game.you.service.GenresServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/genres/ua")
@RequiredArgsConstructor
public class GenresControllerUA {
    final private GenresServiceUA service;
    @GetMapping
    ResponseEntity<List<GenresDTOUA>> getAllGenres () {
        return ResponseEntity.ok().body(service.getAllGenres());
    }
    @PostMapping(value = "/add")
    ResponseEntity<GenresUA> addGenre(@RequestBody GenresUA genre) {

        return ResponseEntity.ok().body(service.addGenre(genre));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<GenresDTOUA> deleteGenre (@PathVariable("id") long id) {
        service.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/update")
    ResponseEntity<GenresDTOUA> updateGenre(@RequestBody GenresDTOUA genresDTOUA) {
        return ResponseEntity.ok().body(service.updateGenre(genresDTOUA));
    }
}
