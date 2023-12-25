package game.you.service;

import game.you.dto.PlatformsDTOUA;
import game.you.entity.PlatformsUA;
import game.you.repository.PlatformsRepositoryUA;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlatformsServiceUA {
    final private PlatformsRepositoryUA repository;
    final  private ModelMapper modelMapper;
    public List<PlatformsDTOUA> getAllPlatforms() {
        return  repository.findAll().stream().map(this::convertToPlatformsDTo).collect(Collectors.toList());
    }


    @Transactional
    public PlatformsUA addPlatform(PlatformsUA platform) {
        return repository.save(platform);
    }

    PlatformsUA convertToPlatforms(PlatformsDTOUA platformsDTO) {
        return  modelMapper.map(platformsDTO, PlatformsUA.class);
    }

    PlatformsDTOUA convertToPlatformsDTo(PlatformsUA platforms) {
        return  modelMapper.map(platforms, PlatformsDTOUA.class);
    }

    @Transactional
    public void deletePlatform(long id) {
        repository.deleteById(id);
    }
    @Transactional
    public PlatformsDTOUA updatePlatform(PlatformsDTOUA platformsDTOUA) {
        PlatformsUA platformsUpdate = repository.findById(platformsDTOUA.getId()).orElseThrow(()-> new EntityNotFoundException("no id platform"));
        if (platformsDTOUA.getTitle()!=null) {
            platformsUpdate.setTitle(platformsDTOUA.getTitle());
        }
        repository.save(platformsUpdate);
        PlatformsDTOUA platformsDTOUAupdate = convertToPlatformsDTo(platformsUpdate);
        return platformsDTOUAupdate;
    }
}
