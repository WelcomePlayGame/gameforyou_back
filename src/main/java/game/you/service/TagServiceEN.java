package game.you.service;
import game.you.dto.TagDTOEN;
import game.you.entity.TagEN;
import game.you.repository.TagRepositoryEN;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagServiceEN {
    final private TagRepositoryEN repository;
    final private ModelMapper modelMapper;
    TagEN convertToTagEN (TagDTOEN tagDTOEN) {
        return  modelMapper.map(tagDTOEN, TagEN.class);
    }


    TagDTOEN convertToTagDTOEN (TagEN tagEN) {
        return  modelMapper.map(tagEN, TagDTOEN.class);
    }
    public List<TagDTOEN> getListTag() {
        return repository.findAll().stream().map(this::convertToTagDTOEN).collect(Collectors.toList());
    }


    @Transactional
    public TagDTOEN addTag(TagEN tagEN) {
        repository.save(tagEN);
        TagDTOEN tagDTOEN = convertToTagDTOEN(tagEN);
        return  tagDTOEN;
    }
}
