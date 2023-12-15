package game.you.controller;

import game.you.dto.CommentDTOEN;
import game.you.dto.CommentDTOPL;
import game.you.service.CommentServiceEN;
import game.you.service.CommentServicePL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment/pl")
@RequiredArgsConstructor
public class CommentControllerPL {

    final  private CommentServicePL service;

    @GetMapping
    ResponseEntity<List<CommentDTOPL>> getListCommentDTO () {
        return ResponseEntity.ok().body(service.getAllCommentEN());
    }

}
