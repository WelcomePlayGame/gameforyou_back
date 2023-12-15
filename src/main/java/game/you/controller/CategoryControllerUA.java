package game.you.controller;

import game.you.dto.CategoryDTOUA;
import game.you.entity.CategoryUA;
import game.you.service.CategoryServiceUA;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/category/ua")
@RequiredArgsConstructor
public class CategoryControllerUA {

   final   private CategoryServiceUA service;



   @GetMapping
   public ResponseEntity <List<CategoryDTOUA>> getListCategoryDTO () {
    return ResponseEntity.ok().body(service.getListCategoryDTo());
   }


    @PostMapping(value = "/add")
    public ResponseEntity<CategoryDTOUA> addCategory (@RequestBody CategoryDTOUA categoryDTO) {

        return ResponseEntity.ok().body(service.addCategory(categoryDTO));
    }


}
