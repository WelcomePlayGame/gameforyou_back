package game.you.controller;

import game.you.dto.DevoloperGameDTOUA;
import game.you.entity.DeveloperGameUA;
import game.you.service.DevoloperServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/developer/ua")
@RequiredArgsConstructor
public class DevoloperControllerUA {
    final  private DevoloperServiceUA service;
    @GetMapping
    ResponseEntity<List<DevoloperGameDTOUA>> getAllDevoloper() {
        return  ResponseEntity.ok().body(service.getAllDevoloper());
    }

    @PostMapping(value = "/add")
    ResponseEntity<DeveloperGameUA> addDevoloper(@RequestBody DeveloperGameUA DeveloperGame) {
        return ResponseEntity.ok().body(service.addDevoloper(DeveloperGame));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<DeveloperGameUA> deleteDeveloper(@PathVariable("id") long id) {
        service.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    ResponseEntity<DeveloperGameUA> updateDeveloper(@RequestBody DeveloperGameUA developerGameUA) {
        return ResponseEntity.ok().body(service.updateDeveloper(developerGameUA));
    }
}
