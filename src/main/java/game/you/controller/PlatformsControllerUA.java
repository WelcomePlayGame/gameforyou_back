package game.you.controller;
import game.you.dto.PlatformsDTOUA;
import game.you.entity.PlatformsUA;
import game.you.service.PlatformsServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platforms/ua")
@RequiredArgsConstructor
public class PlatformsControllerUA {
    final private PlatformsServiceUA service;


    @GetMapping
    ResponseEntity<List<PlatformsDTOUA>> getAllPlatforms () {
        return ResponseEntity.ok().body(service.getAllPlatforms());
    }

    @PostMapping(value = "/add")
    ResponseEntity<PlatformsUA> addPlatform(@RequestBody PlatformsUA platform) {
        return ResponseEntity.ok().body(service.addPlatform(platform));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<PlatformsDTOUA> deletePlatform (@PathVariable("id") long id) {
        service.deletePlatform(id);
        return ResponseEntity.notFound().build();
    }
    @PutMapping(value = "/update")
    ResponseEntity<PlatformsDTOUA> updatePlatform(@RequestBody PlatformsDTOUA platformsDTOUA) {
        return ResponseEntity.ok().body(service.updatePlatform(platformsDTOUA));
    }
}
