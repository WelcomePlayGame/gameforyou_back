package game.you.service;

import game.you.dto.BlogDTOUA;
import game.you.entity.BlogUA;
import game.you.repository.BlogRepositoryEN;
import game.you.repository.BlogRepositoryUA;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogServiceUA {
    final  private BlogRepositoryUA repository;
    final private ModelMapper modelMapper;
    public List<BlogDTOUA> getAllBlogUA () {
        return repository.findAll().stream().map(this::covertToBlogDTO).collect(Collectors.toList());
    }

    BlogUA convertToBlog (BlogDTOUA blogDTOUA) {
        return  modelMapper.map(blogDTOUA, BlogUA.class);
    }

    BlogDTOUA covertToBlogDTO (BlogUA blogUA) {
        return  modelMapper.map(blogUA, BlogDTOUA.class);
    }
}
