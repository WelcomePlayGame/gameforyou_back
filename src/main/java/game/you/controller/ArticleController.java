package game.you.controller;

import game.you.dto.ArticleDTO;
import game.you.entity.Article;
import game.you.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/article")
@RequiredArgsConstructor
public class ArticleController {
    final private ArticleService service;
    final private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getListArticle() {
        return ResponseEntity.ok().body(service.getListArticle().stream().map(this::convertToArticleDTO).collect(Collectors.toList()));
    }
    @PostMapping(value = "/add",consumes = "multipart/form-data")
    public ResponseEntity<Article> addArticle(@RequestPart("article") Article article, @RequestPart("files") List<MultipartFile> files) {

        return ResponseEntity.ok().body(service.addArticle(article));

    }


    Article convertToArticle(ArticleDTO articleDTO) {
        return modelMapper.map(articleDTO, Article.class);
    }

    ArticleDTO convertToArticleDTO(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }
}
