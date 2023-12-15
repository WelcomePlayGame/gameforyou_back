package game.you.controller;

import game.you.dto.PublisherDTOUA;
import game.you.entity.PublisherUA;
import game.you.service.PublihserServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publisher/ua")
@RequiredArgsConstructor
public class PublisherContollerUA {
    final private PublihserServiceUA service;

    @GetMapping
    ResponseEntity<List<PublisherDTOUA>> getAllPublisher() {
        return ResponseEntity.ok().body(service.getAllPublisher());
    }
    @PostMapping(value = "/add")
    ResponseEntity<PublisherUA> addPublisher(@RequestBody PublisherUA publisher) {
        return ResponseEntity.ok().body(service.addPublisher(publisher));
    }
}
