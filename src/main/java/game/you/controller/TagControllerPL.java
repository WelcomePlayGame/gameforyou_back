package game.you.controller;

import game.you.dto.TagDTOPL;
import game.you.entity.TagPL;
import game.you.service.TagServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tag/pl")
@RequiredArgsConstructor
public class TagControllerPL {
    final  private TagServicePL service;
    @GetMapping
    ResponseEntity<List<TagDTOPL>> getListTagDTO () {
        return ResponseEntity.ok().body(service.getListTag());
    }
    @PostMapping(value = "/add")
    ResponseEntity<TagDTOPL> addTagPl (@RequestBody TagPL tagPL) {
        return  ResponseEntity.ok().body(service.addTag(tagPL));
    }
}
