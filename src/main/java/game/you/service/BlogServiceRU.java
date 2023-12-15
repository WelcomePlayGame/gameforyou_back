package game.you.service;


import game.you.dto.BlogDTORU;
import game.you.entity.BlogRU;
import game.you.repository.BlogRepositoryRU;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogServiceRU {
    final private BlogRepositoryRU repository;
    final  private ModelMapper modelMapper;
    public List<BlogDTORU> getListBlogRU () {
        return  repository.findAll().stream().map(this::convertBlogDTORU).collect(Collectors.toList());
    }

    BlogRU convertToBlogRU (BlogDTORU blogDTORU) {
        return modelMapper.map(blogDTORU, BlogRU.class);
    }

    BlogDTORU convertBlogDTORU (BlogRU blogRU) {
        return  modelMapper.map(blogRU, BlogDTORU.class);
    }
}
