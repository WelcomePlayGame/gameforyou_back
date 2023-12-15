package game.you.controller;

import game.you.dto.DevoloperGameDTOEN;
import game.you.dto.DevoloperGameDTOPL;
import game.you.service.DevoloperServiceEN;
import game.you.service.DevoloperServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/developer/pl")
@RequiredArgsConstructor
public class DevoloperControllerPL {
    final  private DevoloperServicePL service;

    @GetMapping
    ResponseEntity<List<DevoloperGameDTOPL>> getListDevoloperDTO () {
        return ResponseEntity.ok().body(service.getAllDevoloper());
    }

    @PostMapping(value = "/add")
    ResponseEntity<DevoloperGameDTOPL> addDeveloperPl (@RequestBody DevoloperGameDTOPL devoloperGameDTOPL) {
        return ResponseEntity.ok().body(service.addDevoloper(devoloperGameDTOPL));
    }
}
