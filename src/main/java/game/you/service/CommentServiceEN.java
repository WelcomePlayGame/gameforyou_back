package game.you.service;

import game.you.dto.CommentDTOEN;
import game.you.entity.CommentEN;
import game.you.entity.GamePostEN;
import game.you.repository.CommentRepositoryEN;
import game.you.repository.GamePostRepositoryEN;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceEN {
    final private CommentRepositoryEN repository;
    final  private GamePostRepositoryEN repositoryEN;
    final  private ModelMapper modelMapper;

    public List<CommentDTOEN> getAllCommentEN () {
        return repository.findAll().stream().map(this::convertToCommentDTOEN).collect(Collectors.toList());
    }


    @Transactional
    public CommentDTOEN addComment(CommentDTOEN commentDTOEN) {
       Optional<GamePostEN> gamePostEN = repositoryEN.findById(commentDTOEN.getId_post());
        CommentEN commentEN = new CommentEN();
        commentEN.setGamePost(gamePostEN.get());
        commentEN.setTitle_comment(commentDTOEN.getTitle_comment());
        commentEN.setDes_comment(commentDTOEN.getDes_comment());
        commentEN.setRating(Long.parseLong(commentDTOEN.getRating()));
        commentEN.setPositiveInputs(commentDTOEN.getPositiveInputs());
        commentEN.setNegativeInputs(commentDTOEN.getNegativeInputs());
        repository.save(commentEN);
        return convertToCommentDTOEN(commentEN);
    }

    CommentEN convertToCommentEN (CommentDTOEN commentDTOEN) {
        return modelMapper.map(commentDTOEN, CommentEN.class);
    }

    CommentDTOEN convertToCommentDTOEN (CommentEN commentEN) {
        return modelMapper.map(commentEN, CommentDTOEN.class);
    }
}
