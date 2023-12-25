package game.you.controller;

import game.you.dto.TagDTOEN;
import game.you.entity.TagEN;
import game.you.service.TagServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tag/en")
@RequiredArgsConstructor
public class TagControllerEN {
    final  private TagServiceEN service;
    @GetMapping
    ResponseEntity<List<TagDTOEN>> getListTagDTO () {
        return ResponseEntity.ok().body(service.getListTag());
    }

    @PostMapping(value = "/add")
    ResponseEntity<TagDTOEN> addTagEN (@RequestBody TagEN tagEN) {
    return  ResponseEntity.ok().body(service.addTag(tagEN));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<TagDTOEN> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/update")
    ResponseEntity<TagDTOEN>update(@RequestBody TagDTOEN tagDTOEN) {
        return ResponseEntity.ok(service.update(tagDTOEN));
    }
}
