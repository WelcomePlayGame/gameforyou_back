package game.you.controller;


import game.you.dto.BlogDTOEN;
import game.you.service.BlogServiceEN;
import game.you.service.BlogServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/blog/en")
@RequiredArgsConstructor
public class BlogControllerEN {
    final private BlogServiceEN service;
    @GetMapping
     ResponseEntity<List<BlogDTOEN>> getListBlogEN () {
        return ResponseEntity.ok().body(service.getListBlogEN());
    }
}
