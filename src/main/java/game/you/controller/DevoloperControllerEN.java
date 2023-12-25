package game.you.controller;

import game.you.dto.DevoloperGameDTOEN;
import game.you.entity.DeveloperGameEN;
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
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<DevoloperGameDTOEN> deleteDeveloper (@PathVariable("id") long id) {
        service.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/update")
    ResponseEntity<DevoloperGameDTOEN> updateDeveloper(@RequestBody DevoloperGameDTOEN devoloperGameDTOEN) {
        return ResponseEntity.ok().body(service.updateDeveloper(devoloperGameDTOEN));
    }
}
