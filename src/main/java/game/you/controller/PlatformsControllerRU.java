package game.you.controller;

import game.you.dto.PlatformsDTORU;
import game.you.service.PlatformsServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platforms/ru")
@RequiredArgsConstructor
public class PlatformsControllerRU {
    final  private PlatformsServiceRU service;

    @GetMapping
    ResponseEntity<List<PlatformsDTORU>> getAllPlatfomsDTO () {
        return  ResponseEntity.ok().body(service.getAllPlatforms());
    }

    @PostMapping(value = "/add")
    ResponseEntity<PlatformsDTORU> addPlatformsDTORU (@RequestBody PlatformsDTORU platformsDTORU) {
        return ResponseEntity.ok().body(service.addPlatform(platformsDTORU));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<PlatformsDTORU> deletePlatform(@PathVariable("id") long id) {
        service.deletePlatform(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/update")
    ResponseEntity<PlatformsDTORU> updatePlatform(@RequestBody PlatformsDTORU platformsDTORU) {
        return ResponseEntity.ok().body(service.updateplatform(platformsDTORU));
    }
}
