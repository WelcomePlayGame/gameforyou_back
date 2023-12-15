package game.you.service;

import game.you.dto.PlatformsDTOEN;
import game.you.entity.PlatformsEN;
import game.you.repository.PlatformsRepositoryEN;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlatformsServiceEN {
    final private PlatformsRepositoryEN repository;
    final  private ModelMapper modelMapper;
    public List<PlatformsDTOEN> getAllPlatforms() {
        return  repository.findAll().stream().map(this::convertToPlatformsDTo).collect(Collectors.toList());
    }


    @Transactional
    public PlatformsDTOEN addPlatform(PlatformsDTOEN platformsDTOEN) {
        PlatformsEN platformsEN = convertToPlatforms(platformsDTOEN);
        repository.save(platformsEN);
        return platformsDTOEN;
    }

    PlatformsEN convertToPlatforms(PlatformsDTOEN platformsDTO) {
        return  modelMapper.map(platformsDTO, PlatformsEN.class);
    }

    PlatformsDTOEN convertToPlatformsDTo(PlatformsEN platforms) {
        return  modelMapper.map(platforms, PlatformsDTOEN.class);
    }
}
