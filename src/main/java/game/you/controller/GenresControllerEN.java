package game.you.controller;


import game.you.dto.GenresDTOEN;
import game.you.service.GenresServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/genres/en")
@RequiredArgsConstructor
public class GenresControllerEN {
    final  private GenresServiceEN service;

    @GetMapping
    ResponseEntity<List<GenresDTOEN>> getListGenreDTO () {
        return  ResponseEntity.ok().body(service.getAllGenres());
    }

    @PostMapping(value = "/add")
    ResponseEntity<GenresDTOEN> addGenresDTOEN (@RequestBody GenresDTOEN genresDTOEN) {
        return  ResponseEntity.ok().body(service.addGenre(genresDTOEN));
    }
}
