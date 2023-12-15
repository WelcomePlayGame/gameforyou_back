package game.you.controller;

import game.you.dto.PublisherDTOEN;
import game.you.service.PublihserServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publisher/en")
@RequiredArgsConstructor
public class PublisherControllerEN {
    final  private PublihserServiceEN service;

    @GetMapping
    ResponseEntity<List<PublisherDTOEN>> getListPublisherDTO () {
        return  ResponseEntity.ok().body(service.getAllPublisher());
    }

    @PostMapping(value = "/add")
    ResponseEntity<PublisherDTOEN> addPublisherDTOEN (@RequestBody PublisherDTOEN publisherDTOEN) {
        return  ResponseEntity.ok().body(service.addPublisher(publisherDTOEN));
    }
}
