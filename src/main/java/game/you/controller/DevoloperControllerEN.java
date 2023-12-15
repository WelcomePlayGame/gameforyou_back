package game.you.controller;

import game.you.dto.DevoloperGameDTOEN;
import game.you.service.DevoloperServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/developer/en")
@RequiredArgsConstructor
public class DevoloperControllerEN {
    final  private DevoloperServiceEN service;

    @GetMapping
    ResponseEntity<List<DevoloperGameDTOEN>> getListDevoloperDTO () {
        return ResponseEntity.ok().body(service.getAllDevoloper());
    }

    @PostMapping(value = "/add")
    ResponseEntity<DevoloperGameDTOEN> addDeveloperEN (@RequestBody DevoloperGameDTOEN devoloperGameDTOEN) {
        return ResponseEntity.ok().body(service.addDevoloper(devoloperGameDTOEN));
    }
}
