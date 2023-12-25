package game.you.service;

import game.you.dto.DevoloperGameDTOUA;
import game.you.entity.DeveloperGameUA;
import game.you.repository.DevoloperRepositoryUA;
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
public class DevoloperServiceUA {
    final private DevoloperRepositoryUA repository;
    final  private ModelMapper modelMapper;
    public List<DevoloperGameDTOUA> getAllDevoloper() {
        return repository.findAll().stream().map(this::convertToDevoloperGameDTO).collect(Collectors.toList());
    }

    @Transactional
    public DeveloperGameUA addDevoloper(DeveloperGameUA DeveloperGame) {
        return repository.save(DeveloperGame);
    }

    DevoloperGameDTOUA convertToDevoloperGameDTO(DeveloperGameUA developerGame) {
        return  modelMapper.map(developerGame, DevoloperGameDTOUA.class);
    }

    DeveloperGameUA convertToDevoloperGame(DevoloperGameDTOUA devoloperGameDTO) {
        return  modelMapper.map(devoloperGameDTO, DeveloperGameUA.class);
    }

    @Transactional
    public void deleteDeveloper(long id) {
    repository.deleteById(id);
    }
    @Transactional
    public DeveloperGameUA updateDeveloper(DeveloperGameUA developerGameUA) {
        DeveloperGameUA developerGameUAupdate = repository.findById(developerGameUA.getId()).orElseThrow(()-> new EntityNotFoundException("no id developer"));
        if (developerGameUA.getTitle()!=null) {
            developerGameUAupdate.setTitle(developerGameUA.getTitle());
        }
        return developerGameUAupdate;
    }
}
