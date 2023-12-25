package game.you.service;

import game.you.dto.PublisherDTOEN;
import game.you.entity.PublisherEN;
import game.you.repository.PublihserRepositoryEN;
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
public class PublihserServiceEN {
    final private PublihserRepositoryEN repository;
    final private ModelMapper modelMapper;
    public List<PublisherDTOEN> getAllPublisher() {
        return repository.findAll().stream().map(this::convertToPublisherDTO).collect(Collectors.toList());
    }

    @Transactional
    public PublisherDTOEN  addPublisher(PublisherDTOEN publisherDTOEN) {
        PublisherEN publisher = convertToPublisher(publisherDTOEN);
        repository.save(publisher);
        return publisherDTOEN;
    }

    PublisherEN convertToPublisher(PublisherDTOEN publisherDTO) {
        return  modelMapper.map(publisherDTO, PublisherEN.class);
    }

    PublisherDTOEN convertToPublisherDTO(PublisherEN publisher) {
        return modelMapper.map(publisher, PublisherDTOEN.class);
    }

    @Transactional
    public void deletePublisher(long id) {
        repository.deleteById(id);
    }
    @Transactional
    public PublisherDTOEN updatePublisher(PublisherDTOEN publisherDTOEN) {
        PublisherEN publisherUpdate = repository.findById(publisherDTOEN.getId()).orElseThrow(()-> new EntityNotFoundException("no id publisher"));
        if (publisherDTOEN.getTitle()!=null) {
            publisherUpdate.setTitle(publisherDTOEN.getTitle());
        }
        repository.save(publisherUpdate);
        PublisherDTOEN publisherDTOENUpdate = convertToPublisherDTO(publisherUpdate);
        return publisherDTOENUpdate;
    }
}
