package game.you.service;

import game.you.dto.CommentDTOPL;
import game.you.entity.CommentPL;
import game.you.repository.CommentRepositoryEN;
import game.you.repository.CommentRepositoryPL;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServicePL {
    final private CommentRepositoryPL repository;
    final  private ModelMapper modelMapper;

    public List<CommentDTOPL> getAllCommentEN () {
        return repository.findAll().stream().map(this::convertToCommentDTOEN).collect(Collectors.toList());
    }

    CommentPL convertToCommentPL (CommentDTOPL commentDTOPL) {
        return modelMapper.map(commentDTOPL, CommentPL.class);
    }

    CommentDTOPL convertToCommentDTOEN (CommentPL commentPL) {
        return modelMapper.map(commentPL, CommentDTOPL.class);
    }
}
