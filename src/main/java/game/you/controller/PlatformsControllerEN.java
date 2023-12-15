package game.you.controller;

import game.you.dto.PlatformsDTOEN;
import game.you.service.PlatformsServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platforms/en")
@RequiredArgsConstructor
public class PlatformsControllerEN {
    final  private PlatformsServiceEN service;

    @GetMapping
    ResponseEntity<List<PlatformsDTOEN>> getAllPlatfomsDTO () {
        return  ResponseEntity.ok().body(service.getAllPlatforms());
    }

    @PostMapping(value = "/add")
    ResponseEntity<PlatformsDTOEN> addPlatformsDTOEN (@RequestBody PlatformsDTOEN platformsDTOEN) {
        return ResponseEntity.ok().body(service.addPlatform(platformsDTOEN));
    }
}
