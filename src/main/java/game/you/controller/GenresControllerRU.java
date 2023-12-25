package game.you.controller;
import game.you.dto.GenresDTORU;
import game.you.service.GenresServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/genres/ru")
@RequiredArgsConstructor
public class GenresControllerRU {
    final  private GenresServiceRU service;

    @GetMapping
    ResponseEntity<List<GenresDTORU>> getListGenreDTO () {
        return  ResponseEntity.ok().body(service.getAllGenres());
    }

    @PostMapping(value = "/add")
    ResponseEntity<GenresDTORU> addGenresDTORU (@RequestBody GenresDTORU genresDTORU) {
        return ResponseEntity.ok().body(service.addGenre(genresDTORU));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<GenresDTORU> deleteGenre (@PathVariable("id") long id) {
        service.deleteGenre(id);
       return ResponseEntity.notFound().build();
    }
    @PutMapping(value = "/update")
    ResponseEntity<GenresDTORU> updateGenre(@RequestBody GenresDTORU genresDTORU) {
        return ResponseEntity.ok().body(service.updateGenre(genresDTORU));
    }
}
