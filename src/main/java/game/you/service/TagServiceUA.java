package game.you.service;

import game.you.dto.TagDTOEN;
import game.you.dto.TagDTOUA;
import game.you.entity.TagEN;
import game.you.entity.TagUA;
import game.you.repository.TagRepositoryUA;
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
public class TagServiceUA {
    final private TagRepositoryUA repository;
    final private ModelMapper modelMapper;
    public List<TagDTOUA> getListTag() {
        return repository.findAll().stream().map(this::convertToTagDTOUA).collect(Collectors.toList());
    }
    @Transactional
    public TagDTOUA addTag(TagUA tagUA) {
        repository.save(tagUA);
        TagDTOUA tagDTOUA = convertToTagDTOUA(tagUA);
        return  tagDTOUA;
    }

    TagUA convertTagUA (TagDTOUA tagDTOUA)  {
        return modelMapper.map(tagDTOUA, TagUA.class);
    }
    TagDTOUA convertToTagDTOUA (TagUA tagUA) {
        return modelMapper.map(tagUA, TagDTOUA.class);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public TagDTOUA update(TagDTOUA tagDTOUA) {
        TagUA tagUpdate = repository.findById(tagDTOUA.getId()).orElseThrow(()-> new EntityNotFoundException("no id tag"));
        if (tagDTOUA.getTitle()!=null) {
            tagUpdate.setTitle(tagUpdate.getTitle());
        }
        repository.save(tagUpdate);
        TagDTOUA tagDTOUAUpdate = convertToTagDTOUA(tagUpdate);
        return tagDTOUAUpdate;
    }
}
