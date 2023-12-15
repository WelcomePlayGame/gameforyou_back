package game.you.controller;



import game.you.dto.BlogDTORU;
import game.you.service.BlogServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/blog/ru")
@RequiredArgsConstructor
public class BlogControllerRU {
    final private BlogServiceRU service;
    @GetMapping
     ResponseEntity<List<BlogDTORU>> getListBlogEN () {
        return ResponseEntity.ok().body(service.getListBlogRU());
    }
}
