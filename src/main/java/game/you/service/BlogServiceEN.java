package game.you.service;

import game.you.dto.BlogDTOEN;
import game.you.dto.BlogDTOUA;
import game.you.entity.BlogEN;
import game.you.entity.BlogUA;
import game.you.repository.BlogRepositoryEN;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogServiceEN {
    final private BlogRepositoryEN repository;
    final  private ModelMapper modelMapper;
    public List<BlogDTOEN> getListBlogEN () {
        return  repository.findAll().stream().map(this::convertBlogDTOEN).collect(Collectors.toList());
    }

    BlogEN convertToBlogUA (BlogDTOEN blogDTOEN) {
        return modelMapper.map(blogDTOEN, BlogEN.class);
    }

    BlogDTOEN convertBlogDTOEN (BlogEN blogEN) {
        return  modelMapper.map(blogEN, BlogDTOEN.class);
    }
}
