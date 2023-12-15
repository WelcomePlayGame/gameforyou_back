package game.you.controller;

import game.you.dto.CommentDTOUA;
import game.you.service.CommentServiceUA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment/ua")
@RequiredArgsConstructor
public class CommentControllerUA {
    final private CommentServiceUA service;

    @GetMapping
    ResponseEntity<List<CommentDTOUA>> getListCommentDTO () {
        return  ResponseEntity.ok().body(service.getListCommentDTO());
    }
}
