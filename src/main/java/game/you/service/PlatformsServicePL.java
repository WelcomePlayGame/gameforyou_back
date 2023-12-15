package game.you.service;

import game.you.dto.PlatformsDTOPL;
import game.you.entity.PlatformsPL;
import game.you.repository.PlatformsRepositoryPL;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlatformsServicePL {
    final private PlatformsRepositoryPL repository;
    final  private ModelMapper modelMapper;
    public List<PlatformsDTOPL> getAllPlatforms() {
        return  repository.findAll().stream().map(this::convertToPlatformsDTo).collect(Collectors.toList());
    }


    @Transactional
    public PlatformsDTOPL addPlatform(PlatformsDTOPL platformsDTOPL) {
        PlatformsPL platform = convertToPlatforms(platformsDTOPL);
        repository.save(platform);
        return platformsDTOPL;
    }

    PlatformsPL convertToPlatforms(PlatformsDTOPL platformsDTO) {
        return  modelMapper.map(platformsDTO, PlatformsPL.class);
    }

    PlatformsDTOPL convertToPlatformsDTo(PlatformsPL platforms) {
        return  modelMapper.map(platforms, PlatformsDTOPL.class);
    }
}
