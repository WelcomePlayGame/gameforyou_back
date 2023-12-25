package game.you.service;

import game.you.dto.TagDTOPL;
import game.you.entity.TagPL;
import game.you.repository.TagRepositoryPL;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagServicePL {
    final private TagRepositoryPL repository;
    final private ModelMapper modelMapper;
    public List<TagDTOPL> getListTag() {
        return repository.findAll().stream().map(this::convertToTagDTOPL).collect(Collectors.toList());
    }

    @Transactional
    public TagDTOPL addTag(TagPL tagPL) {
        repository.save(tagPL);
        TagDTOPL tagDTOPL = convertToTagDTOPL(tagPL);
        return  tagDTOPL;
    }
    TagPL convertToTagPL (TagDTOPL tagDTOPL) {
    return  modelMapper.map(tagDTOPL, TagPL.class);
    }

    TagDTOPL convertToTagDTOPL (TagPL tagPL) {
        return  modelMapper.map(tagPL, TagDTOPL.class);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public TagDTOPL update(TagDTOPL tagDTOPL) {
        TagPL tagUpdate = repository.findById(tagDTOPL.getId()).orElseThrow(()-> new EntityNotFoundException("no id tag"));
        if (tagDTOPL.getTitle()!=null) {
            tagUpdate.setTitle(tagDTOPL.getTitle());
        }
        repository.save(tagUpdate);
        TagDTOPL tagDTOPLUpdate = convertToTagDTOPL(tagUpdate);
        return tagDTOPLUpdate;
    }
}
