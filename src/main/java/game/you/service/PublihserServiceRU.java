package game.you.service;


import game.you.dto.PublisherDTORU;
import game.you.entity.PublisherRU;
import game.you.repository.PublihserRepositoryRU;
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
public class PublihserServiceRU {
    final private PublihserRepositoryRU repository;
    final private ModelMapper modelMapper;
    public List<PublisherDTORU> getAllPublisher() {
        return repository.findAll().stream().map(this::convertToPublisherDTO).collect(Collectors.toList());
    }

    @Transactional
    public PublisherRU addPublisher(PublisherRU publisher) {

        return repository.save(publisher);
    }




    PublisherRU convertToPublisher(PublisherDTORU publisherDTO) {
        return  modelMapper.map(publisherDTO, PublisherRU.class);
    }

    PublisherDTORU convertToPublisherDTO(PublisherRU publisher) {
        return modelMapper.map(publisher, PublisherDTORU.class);
    }

    @Transactional
    public void deletePublisher(long id) {
    repository.deleteById(id);
    }

    @Transactional
    public PublisherDTORU update(PublisherDTORU publisherDTORU) {
        PublisherRU publisherUpdate = repository.findById(publisherDTORU.getId()).orElseThrow(()-> new EntityNotFoundException("no id publisher"));
        if (publisherDTORU.getTitle()!=null) {
            publisherUpdate.setTitle(publisherDTORU.getTitle());
        }
        repository.save(publisherUpdate);
        PublisherDTORU publisherDTORUupdate = convertToPublisherDTO(publisherUpdate);
        return publisherDTORUupdate;
    }
}
