package game.you.service;


import game.you.dto.CommentDTORU;
import game.you.entity.CommentRU;
import game.you.repository.CommentRepositoryRU;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceRU {
    final private CommentRepositoryRU repository;
    final  private ModelMapper modelMapper;

    public List<CommentDTORU> getAllCommentRU () {
        return repository.findAll().stream().map(this::convertToCommentDTORU).collect(Collectors.toList());
    }

    CommentRU convertToCommentRU (CommentDTORU commentDTORU) {
        return modelMapper.map(commentDTORU, CommentRU.class);
    }

    CommentDTORU convertToCommentDTORU (CommentRU commentRU) {
        return modelMapper.map(commentRU, CommentDTORU.class);
    }
}
