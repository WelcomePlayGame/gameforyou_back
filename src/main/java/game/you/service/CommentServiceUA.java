package game.you.service;

import game.you.dto.CommentDTOUA;
import game.you.entity.CommentUA;
import game.you.repository.CommentRepositoryUA;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceUA {
    final private CommentRepositoryUA repository;
    final  private ModelMapper modelMapper;

    public List<CommentDTOUA> getListCommentDTO () {
        return  repository.findAll().stream().map(this::convertToCommentDTOUA).collect(Collectors.toList());
    }

    CommentUA convertToCommentUA(CommentDTOUA commentDTOUA ) {
        return  modelMapper.map(commentDTOUA, CommentUA.class);
    }

    CommentDTOUA convertToCommentDTOUA (CommentUA commentUA) {
        return modelMapper.map(commentUA, CommentDTOUA.class);
    }
}
