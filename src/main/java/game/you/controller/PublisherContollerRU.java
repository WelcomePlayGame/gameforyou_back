package game.you.controller;

import game.you.dto.PublisherDTORU;
import game.you.entity.PublisherRU;
import game.you.service.PublihserServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publisher/ru")
@RequiredArgsConstructor
public class PublisherContollerRU {
    final private PublihserServiceRU service;

    @GetMapping
    ResponseEntity<List<PublisherDTORU>> getAllPublisher() {
        return ResponseEntity.ok().body(service.getAllPublisher());
    }
    @PostMapping(value = "/add")
    ResponseEntity<PublisherRU> addPublisher(@RequestBody PublisherRU publisher) {
        return ResponseEntity.ok().body(service.addPublisher(publisher));
    }
}
