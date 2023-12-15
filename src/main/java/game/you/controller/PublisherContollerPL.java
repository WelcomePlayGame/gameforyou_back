package game.you.controller;

import game.you.dto.PublisherDTOEN;
import game.you.dto.PublisherDTOPL;
import game.you.entity.PublisherPL;
import game.you.service.PublihserServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publisher/pl")
@RequiredArgsConstructor
public class PublisherContollerPL {
    final private PublihserServicePL service;

    @GetMapping
    ResponseEntity<List<PublisherDTOPL>> getAllPublisher() {
        return ResponseEntity.ok().body(service.getAllPublisher());
    }
    @PostMapping(value = "/add")
    ResponseEntity<PublisherPL> addPublisher(@RequestBody PublisherPL publisher) {
        return ResponseEntity.ok().body(service.addPublisher(publisher));
    }
}
