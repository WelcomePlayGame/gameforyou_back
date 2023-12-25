package game.you.controller;
import game.you.service.SiteMapServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sitemap/en")
@RequiredArgsConstructor
public class SiteMapENController {
    private final SiteMapServiceEN service;
    @GetMapping
    ResponseEntity<List<String>> getSiteMap () {
        return ResponseEntity.ok().body(service.getSiteMap());
    }
}
