package game.you.service;

import game.you.dto.DevoloperGameDTOEN;
import game.you.entity.DeveloperGameEN;
import game.you.repository.DevoloperRepositoryEN;
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
public class DevoloperServiceEN {
    final private DevoloperRepositoryEN repository;
    final  private ModelMapper modelMapper;
    public List<DevoloperGameDTOEN> getAllDevoloper() {
        return repository.findAll().stream().map(this::convertToDevoloperGameDTO).collect(Collectors.toList());
    }

    @Transactional
    public DevoloperGameDTOEN addDevoloper(DevoloperGameDTOEN devoloperGameDTOEN) {
        DeveloperGameEN developerGameEN = convertToDevoloperGame(devoloperGameDTOEN);
        repository.save(developerGameEN);
        return devoloperGameDTOEN;
    }

    DevoloperGameDTOEN convertToDevoloperGameDTO(DeveloperGameEN developerGame) {
        return  modelMapper.map(developerGame, DevoloperGameDTOEN.class);
    }

    DeveloperGameEN convertToDevoloperGame(DevoloperGameDTOEN devoloperGameDTO) {
        return  modelMapper.map(devoloperGameDTO, DeveloperGameEN.class);
    }

    @Transactional
    public void deleteDeveloper(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public DevoloperGameDTOEN updateDeveloper(DevoloperGameDTOEN devoloperGameDTOEN) {
        DeveloperGameEN developerGameUpdate = repository.findById(devoloperGameDTOEN.getId()).orElseThrow(()-> new EntityNotFoundException("no id developer"));
        if (devoloperGameDTOEN.getTitle()!=null) {
            developerGameUpdate.setTitle(devoloperGameDTOEN.getTitle());
        }
        repository.save(developerGameUpdate);
        DevoloperGameDTOEN devoloperGameDTOENupdate = convertToDevoloperGameDTO(developerGameUpdate);
        return devoloperGameDTOENupdate;
    }
}
