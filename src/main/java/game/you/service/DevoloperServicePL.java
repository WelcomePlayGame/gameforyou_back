package game.you.service;


import game.you.dto.DevoloperGameDTOPL;
import game.you.entity.DeveloperGamePL;
import game.you.repository.DevoloperRepositoryPL;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DevoloperServicePL {
    final private DevoloperRepositoryPL repository;
    final  private ModelMapper modelMapper;
    public List<DevoloperGameDTOPL> getAllDevoloper() {
        return repository.findAll().stream().map(this::convertToDevoloperGameDTO).collect(Collectors.toList());
    }

    @Transactional
    public DevoloperGameDTOPL addDevoloper(DevoloperGameDTOPL devoloperGameDTOPL) {
        DeveloperGamePL developerGamePL = convertToDevoloperGame(devoloperGameDTOPL);
        repository.save(developerGamePL);
        return devoloperGameDTOPL;
    }

    DevoloperGameDTOPL convertToDevoloperGameDTO(DeveloperGamePL developerGame) {
        return  modelMapper.map(developerGame, DevoloperGameDTOPL.class);
    }

    DeveloperGamePL convertToDevoloperGame(DevoloperGameDTOPL devoloperGameDTO) {
        return  modelMapper.map(devoloperGameDTO, DeveloperGamePL.class);
    }
}
