package game.you.service;


import game.you.dto.PublisherDTOPL;
import game.you.entity.PublisherPL;
import game.you.repository.PublihserRepositoryPL;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublihserServicePL {
    final private PublihserRepositoryPL repository;
    final private ModelMapper modelMapper;
    public List<PublisherDTOPL> getAllPublisher() {
        return repository.findAll().stream().map(this::convertToPublisherDTO).collect(Collectors.toList());
    }

    @Transactional
    public PublisherPL addPublisher(PublisherPL publisher) {

        return repository.save(publisher);
    }




    PublisherPL convertToPublisher(PublisherDTOPL publisherDTO) {
        return  modelMapper.map(publisherDTO, PublisherPL.class);
    }

    PublisherDTOPL convertToPublisherDTO(PublisherPL publisher) {
        return modelMapper.map(publisher, PublisherDTOPL.class);
    }
}
