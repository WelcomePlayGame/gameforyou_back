package game.you.controller;

import game.you.dto.CategoryDTOEN;
import game.you.service.CategoryServiceEN;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category/en")
@RequiredArgsConstructor
public class CategoryControllerEN {
    final  private CategoryServiceEN service;

    @GetMapping
    ResponseEntity<List<CategoryDTOEN>> getListCategoryEN () {
        return ResponseEntity.ok().body(service.getListCategoryDTo());
    }
    @PostMapping(value = "/add")
    ResponseEntity<CategoryDTOEN> addCategoryEN (@RequestBody CategoryDTOEN categoryDTOEN) {
        return ResponseEntity.ok().body(service.addCategory(categoryDTOEN));
    }
}
