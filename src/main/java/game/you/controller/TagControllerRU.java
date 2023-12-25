package game.you.controller;

import game.you.dto.TagDTORU;
import game.you.entity.TagRU;
import game.you.service.TagServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tag/ru")
@RequiredArgsConstructor
public class TagControllerRU {
    final  private TagServiceRU service;
    @GetMapping
    ResponseEntity<List<TagDTORU>> getListTagDTO () {
        return ResponseEntity.ok().body(service.getListTag());
    }
    @PostMapping(value = "/add")
    ResponseEntity<TagDTORU> addTagEN (@RequestBody TagRU tagRU) {
        return  ResponseEntity.ok().body(service.addTag(tagRU));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<TagDTORU> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update")
    ResponseEntity<TagDTORU>update(@RequestBody TagDTORU tagDTORU) {
        return ResponseEntity.ok().body(service.update(tagDTORU));
    }
}
