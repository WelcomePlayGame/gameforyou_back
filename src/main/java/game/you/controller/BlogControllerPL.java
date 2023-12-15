package game.you.controller;


import game.you.dto.BlogDTOEN;
import game.you.dto.BlogDTOPL;
import game.you.service.BlogServiceEN;
import game.you.service.BlogServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/blog/pl")
@RequiredArgsConstructor
public class BlogControllerPL {
    final private BlogServicePL service;
    @GetMapping
     ResponseEntity<List<BlogDTOPL>> getListBlogEN () {
        return ResponseEntity.ok().body(service.getListBlogEN());
    }
}
