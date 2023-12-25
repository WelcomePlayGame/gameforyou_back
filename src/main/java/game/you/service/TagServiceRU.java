package game.you.service;

import game.you.dto.TagDTORU;
import game.you.entity.TagRU;
import game.you.repository.TagRepositoryRU;
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
public class TagServiceRU {
    final private TagRepositoryRU repository;
    final private ModelMapper modelMapper;
    public List<TagDTORU> getListTag() {
        return repository.findAll().stream().map(this::convertToTagDTORU).collect(Collectors.toList());
    }
    @Transactional
    public TagDTORU addTag(TagRU tagRU) {
        repository.save(tagRU);
        TagDTORU tagDTORU = convertToTagDTORU(tagRU);
        return  tagDTORU;
    }

    TagRU convertToTagRU (TagDTORU tagDTORU) {
    return  modelMapper.map(tagDTORU, TagRU.class);
    }

    TagDTORU convertToTagDTORU (TagRU tagRU) {
        return  modelMapper.map(tagRU, TagDTORU.class);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public TagDTORU update(TagDTORU tagDTORU) {
        TagRU tagRUUpdate = repository.findById(tagDTORU.getId()).orElseThrow(()-> new EntityNotFoundException("no id tag"));
        if (tagDTORU.getTitle()!=null) {
            tagRUUpdate.setTitle(tagDTORU.getTitle());
        }
        repository.save(tagRUUpdate);
        TagDTORU tagDTORUUpdate = convertToTagDTORU(tagRUUpdate);
        return tagDTORUUpdate;
    }

}
