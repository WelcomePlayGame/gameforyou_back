package game.you.controller;


import game.you.dto.DevoloperGameDTORU;
import game.you.service.DevoloperServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/developer/ru")
@RequiredArgsConstructor
public class DevoloperControllerRU {
    final  private DevoloperServiceRU service;

    @GetMapping
    ResponseEntity<List<DevoloperGameDTORU>> getListDevoloperDTO () {
        return ResponseEntity.ok().body(service.getAllDevoloper());
    }

    @PostMapping(value = "/add")
    ResponseEntity<DevoloperGameDTORU> addDeveloper (@RequestBody DevoloperGameDTORU devoloperGameDTORU) {
    return   ResponseEntity.ok().body(service.addDevoloper(devoloperGameDTORU));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<DevoloperGameDTORU> deleteDeveloper (@PathVariable("id") long id) {
        service.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/update")
    ResponseEntity<DevoloperGameDTORU> updateDeveloper(@RequestBody DevoloperGameDTORU devoloperGameDTORU) {

        return ResponseEntity.ok().body(service.updateDeveloper(devoloperGameDTORU));
    }

}
