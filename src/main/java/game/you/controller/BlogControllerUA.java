package game.you.controller;

import game.you.dto.BlogDTOUA;
import game.you.service.BlogServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/blog/ua")
@RequiredArgsConstructor
public class BlogControllerUA {
    final private BlogServiceUA service;
    @GetMapping
    ResponseEntity<List<BlogDTOUA>> getListBlogUA () {
        return ResponseEntity.ok().body(service.getAllBlogUA());
    }
}
