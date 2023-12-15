package game.you.controller;

import game.you.dto.TagDTOUA;
import game.you.entity.TagUA;
import game.you.service.TagServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tag/ua")
@RequiredArgsConstructor
public class TagControllerUA {
    final private TagServiceUA service;

    @GetMapping
    ResponseEntity<List<TagDTOUA>> getListTagDTO () {
        return ResponseEntity.ok().body(service.getListTag());
    }
    @PostMapping(value = "/add")
    ResponseEntity<TagDTOUA> addTagUA (@RequestBody TagUA tagUA) {
        return  ResponseEntity.ok().body(service.addTag(tagUA));
    }
}
