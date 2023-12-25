package game.you.service;

import game.you.dto.PlatformsDTORU;
import game.you.entity.PlatformsRU;
import game.you.repository.PlatformsRepositoryRU;
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
public class PlatformsServiceRU {
    final private PlatformsRepositoryRU repository;
    final  private ModelMapper modelMapper;
    public List<PlatformsDTORU> getAllPlatforms() {
        return  repository.findAll().stream().map(this::convertToPlatformsDTo).collect(Collectors.toList());
    }


    @Transactional
    public PlatformsDTORU addPlatform(PlatformsDTORU platformsDTORU) {
        PlatformsRU platform = convertToPlatforms(platformsDTORU);
        repository.save(platform);
        return platformsDTORU;
    }

    PlatformsRU convertToPlatforms(PlatformsDTORU platformsDTO) {
        return  modelMapper.map(platformsDTO, PlatformsRU.class);
    }

    PlatformsDTORU convertToPlatformsDTo(PlatformsRU platforms) {
        return  modelMapper.map(platforms, PlatformsDTORU.class);
    }
    @Transactional
    public void deletePlatform(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public PlatformsDTORU updateplatform(PlatformsDTORU platformsDTORU) {
        PlatformsRU platformsUpdate = repository.findById(platformsDTORU.getId()).orElseThrow(()-> new EntityNotFoundException("no id platform"));
        if (platformsDTORU.getTitle()!=null) {
            platformsUpdate.setTitle(platformsDTORU.getTitle());
        }
        repository.save(platformsUpdate);
        PlatformsDTORU platformsDTORUupdate = convertToPlatformsDTo(platformsUpdate);
        return platformsDTORUupdate;
    }
}
