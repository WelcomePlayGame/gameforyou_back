package game.you.controller;

import game.you.dto.TagDTO;
import game.you.entity.Tag;
import game.you.service.TagService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tag")
@RequiredArgsConstructor
public class TagController {
    final private TagService service;
    final private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity <List<TagDTO>> tagList () {
        return ResponseEntity.ok().body(service.getListTag().stream().map(this::convertToTagDTO).collect(Collectors.toList()));
    }


    Tag convertToTag(TagDTO tagDTO) {
        return modelMapper.map(tagDTO, Tag.class);
    }

    TagDTO convertToTagDTO(Tag tag) {
        return modelMapper.map(tag, TagDTO.class);
    }

}
