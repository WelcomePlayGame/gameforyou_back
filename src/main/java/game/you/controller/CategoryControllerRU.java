package game.you.controller;

import game.you.dto.CategoryDTORU;
import game.you.dto.CategoryDTOUA;
import game.you.service.CategoryServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category/ru")
@RequiredArgsConstructor
public class CategoryControllerRU {
    final  private CategoryServiceRU service;

    @GetMapping
    ResponseEntity<List<CategoryDTORU>> getListCategoryRU () {
        return ResponseEntity.ok().body(service.getListCategoryDTo());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<CategoryDTORU> addCategory (@RequestBody CategoryDTORU categoryDTO) {

        return ResponseEntity.ok().body(service.addCategory(categoryDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<CategoryDTORU> deleteCategory(@PathVariable("id") long id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/update")
    ResponseEntity<CategoryDTORU> updateCategory(@RequestBody CategoryDTORU categoryDTORU) {
        return ResponseEntity.ok().body(service.updateCategory(categoryDTORU));
    }
}
