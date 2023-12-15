package game.you.controller;

import game.you.dto.CommentDTOEN;
import game.you.entity.CommentEN;
import game.you.service.CommentServiceEN;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment/en")
@RequiredArgsConstructor
@Slf4j
public class CommentControllerEN {

    final  private CommentServiceEN service;

    @GetMapping
    ResponseEntity<List<CommentDTOEN>> getListCommentDTO () {
        return ResponseEntity.ok().body(service.getAllCommentEN());
    }

    @PostMapping(value = "/add")
    ResponseEntity<CommentDTOEN> addComment (@RequestBody CommentDTOEN commentDTOEN) {
        log.info(commentDTOEN.getPositiveInputs().toString());
        return ResponseEntity.ok().body(service.addComment(commentDTOEN));
    }
}
