package game.you.controller;

import game.you.dto.CommentDTOPL;
import game.you.dto.CommentDTORU;
import game.you.service.CommentServicePL;
import game.you.service.CommentServiceRU;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment/ru")
@RequiredArgsConstructor
public class CommentControllerRU {

    final  private CommentServiceRU service;

    @GetMapping
    ResponseEntity<List<CommentDTORU>> getListCommentDTO () {
        return ResponseEntity.ok().body(service.getAllCommentRU());
    }

}
