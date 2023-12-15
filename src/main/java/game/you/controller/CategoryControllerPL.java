package game.you.controller;

import game.you.dto.CategoryDTOEN;
import game.you.dto.CategoryDTOPL;
import game.you.service.CategoryServiceEN;
import game.you.service.CategoryServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category/pl")
@RequiredArgsConstructor
public class CategoryControllerPL {
    final  private CategoryServicePL service;

    @GetMapping
    ResponseEntity<List<CategoryDTOPL>> getListCategoryEN () {
        return ResponseEntity.ok().body(service.getListCategoryDTo());
    }

    @PostMapping(value = "/add")
    ResponseEntity<CategoryDTOPL> addCategoryPL (@RequestBody CategoryDTOPL categoryDTOPL) {
        return ResponseEntity.ok().body(service.addCategory(categoryDTOPL));
    }
}
