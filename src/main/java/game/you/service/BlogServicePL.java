package game.you.service;

import game.you.dto.BlogDTOPL;
import game.you.entity.BlogPL;
import game.you.repository.BlogRepositoryPL;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogServicePL {
    final private BlogRepositoryPL repository;
    final  private ModelMapper modelMapper;
    public List<BlogDTOPL> getListBlogEN () {
        return  repository.findAll().stream().map(this::convertBlogDTOEN).collect(Collectors.toList());
    }

    BlogPL convertToBlogUA (BlogDTOPL blogDTOPL) {
        return modelMapper.map(blogDTOPL , BlogPL.class);
    }

    BlogDTOPL convertBlogDTOEN (BlogPL blogEN) {
        return  modelMapper.map(blogEN, BlogDTOPL.class);
    }
}
