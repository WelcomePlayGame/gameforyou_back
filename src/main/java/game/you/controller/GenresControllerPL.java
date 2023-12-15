package game.you.controller;


import game.you.dto.GenresDTOEN;
import game.you.dto.GenresDTOPL;
import game.you.service.GenresServiceEN;
import game.you.service.GenresServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/genres/pl")
@RequiredArgsConstructor
public class GenresControllerPL {
    final  private GenresServicePL service;

    @GetMapping
    ResponseEntity<List<GenresDTOPL>> getListGenreDTO () {
        return  ResponseEntity.ok().body(service.getAllGenres());
    }


    @PostMapping(value = "/add")
    ResponseEntity<GenresDTOPL> addGenresDTOPL (@RequestBody GenresDTOPL genresDTOPL) {
        return ResponseEntity.ok().body(service.addGenre(genresDTOPL));
    }
}
