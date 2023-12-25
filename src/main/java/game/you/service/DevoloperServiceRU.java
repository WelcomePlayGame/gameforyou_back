package game.you.service;

import game.you.dto.DevoloperGameDTORU;
import game.you.entity.DeveloperGamePL;
import game.you.entity.DeveloperGameRU;
import game.you.repository.DevoloperRepositoryRU;
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
public class DevoloperServiceRU {
    final private DevoloperRepositoryRU repository;
    final  private ModelMapper modelMapper;
    public List<DevoloperGameDTORU> getAllDevoloper() {
        return repository.findAll().stream().map(this::convertToDevoloperGameDTO).collect(Collectors.toList());
    }

    @Transactional
    public DevoloperGameDTORU addDevoloper(DevoloperGameDTORU devoloperGameDTORU) {
        DeveloperGameRU developerGameRU = convertToDevoloperGame(devoloperGameDTORU);
        repository.save(developerGameRU);
        return devoloperGameDTORU;
    }

    DevoloperGameDTORU convertToDevoloperGameDTO(DeveloperGameRU developerGame) {
        return  modelMapper.map(developerGame, DevoloperGameDTORU.class);
    }

    DeveloperGameRU convertToDevoloperGame(DevoloperGameDTORU devoloperGameDTO) {
        return  modelMapper.map(devoloperGameDTO, DeveloperGameRU.class);
    }
    @Transactional
    public void deleteDeveloper(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DevoloperGameDTORU updateDeveloper(DevoloperGameDTORU devoloperGameDTORU) {
        DeveloperGameRU developerUpdate = repository.findById(devoloperGameDTORU.getId()).orElseThrow(()-> new EntityNotFoundException("no id developer"));
        if (devoloperGameDTORU.getTitle()!=null) {
            developerUpdate.setTitle(devoloperGameDTORU.getTitle());
        }
        repository.save(developerUpdate);
        DevoloperGameDTORU devoloperGameDTORUUpdate = convertToDevoloperGameDTO(developerUpdate);
        return devoloperGameDTORUUpdate;
    }
}
