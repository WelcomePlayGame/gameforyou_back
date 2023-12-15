package game.you.service;

import game.you.dto.PublisherDTOUA;
import game.you.entity.PublisherUA;
import game.you.repository.PublihserRepositoryUA;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PublihserServiceUA {
    final private PublihserRepositoryUA repository;
    final private ModelMapper modelMapper;
    public List<PublisherDTOUA> getAllPublisher() {
        return repository.findAll().stream().map(this::convertToPublisherDTO).collect(Collectors.toList());
    }

    @Transactional
    public PublisherUA addPublisher(PublisherUA publisher) {
        return repository.save(publisher);
    }

    PublisherUA convertToPublisher(PublisherDTOUA publisherDTO) {
        return  modelMapper.map(publisherDTO, PublisherUA.class);
    }

    PublisherDTOUA convertToPublisherDTO(PublisherUA publisher) {
        return modelMapper.map(publisher, PublisherDTOUA.class);
    }
}
