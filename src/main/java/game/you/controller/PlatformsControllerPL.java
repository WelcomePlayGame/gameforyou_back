package game.you.controller;

import game.you.dto.PlatformsDTOEN;
import game.you.dto.PlatformsDTOPL;
import game.you.service.PlatformsServiceEN;
import game.you.service.PlatformsServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platforms/pl")
@RequiredArgsConstructor
public class PlatformsControllerPL {
    final  private PlatformsServicePL service;

    @GetMapping
    ResponseEntity<List<PlatformsDTOPL>> getAllPlatfomsDTO () {
        return  ResponseEntity.ok().body(service.getAllPlatforms());
    }

    @PostMapping(value = "/add")
    ResponseEntity<PlatformsDTOPL> addPlatformsDTOPL (@RequestBody PlatformsDTOPL platformsDTOPL) {
        return  ResponseEntity.ok().body(service.addPlatform(platformsDTOPL));
    }
}
