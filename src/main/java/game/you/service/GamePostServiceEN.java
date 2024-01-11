package game.you.service;

import game.you.dto.GamePostByIdDTOEN;
import game.you.dto.GamePostDTOEN;
import game.you.entity.*;
import game.you.repository.GamePostDesUrlRepositoryEN;
import game.you.repository.GamePostRepositoryEN;
import game.you.repository.GenresRepositotyEN;
import game.you.repository.PlatformsRepositoryEN;
import game.you.unit.ForkWithFile;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class GamePostServiceEN implements ForkWithFile {
    final  private GamePostRepositoryEN repository;
    final private GamePostDesUrlRepositoryEN repository_des;
    final  private ModelMapper modelMapper;
    final  private PlatformsRepositoryEN platformsRepository;
    final private GenresRepositotyEN genresRepositoty;

    @Value("${server.url.gamepost_poster}")
    private String URL_CATALOG;
    @Value("${file.upload.gamepost_poster}")
    private String UPLOAD_CATALOG;
    @Value("${base_url}")
    private String BASE_URL;


    @Cacheable(value = "game_en_all", key = "'game_en_all'+#id")
    public List <GamePostDTOEN> getAllFGame(Long id) {
        return repository.findAllCustom(id).stream().map(this::convertToGamePostDTO).collect(Collectors.toList());
    }

    @Transactional
    public GamePostEN addGamePost(GamePostEN gamePost,
                                  List<MultipartFile> posterPhoto,
                                  List<Long> ids,
                                  MultipartFile photo,
                                  List <String> genresSet,
                                  List<String> platformsSet
                                ) throws IOException {
        String latinTitle = StringUtils.stripAccents(gamePost.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        GamePosterHorizontalEN posterHorizontal = new GamePosterHorizontalEN();
        for(MultipartFile file : posterPhoto) {
            String name = generateNameFile(file);
            createDirectory(UPLOAD_CATALOG+"/en/"+latinTitle, name, file);
             if (file.getOriginalFilename().contains("1024x768") ) {
                posterHorizontal.setPoster_1024x768(BASE_URL+URL_CATALOG+"/en/"+latinTitle+"/"+name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                posterHorizontal.setPoster_480x320(BASE_URL+URL_CATALOG+"/en/"+latinTitle+"/"+name);
            }
        }
        Set<GamePost_des_urlsEN> list = new HashSet<>();
        for (Long id : ids) {
        Optional<GamePost_des_urlsEN> game_des = Optional.ofNullable(repository_des.findById(id).orElseThrow(() -> new EntityNotFoundException("No id GamePost_Id for Des")));
        game_des.get().setGamePost(gamePost);
        list.add(game_des.get());
        }
        gamePost.setGamePost_des_urls(list);
        GamePosterVerticalEN photoVertical = new GamePosterVerticalEN();
        String name = generateNameFile(photo);
        createDirectory(UPLOAD_CATALOG+"/en/"+latinTitle, name, photo);
        photoVertical.setPoster_300x300(BASE_URL+URL_CATALOG+"/en/"+latinTitle+"/"+name);
        Set<GenresEN> genresIdSet = new HashSet<>();
        for (String genre : genresSet) {
            long id = Long.parseLong(genre);
            Optional<GenresEN> genreid = Optional.of(genresRepositoty.findById(id).orElseThrow(()->new EntityNotFoundException("Not genre")));
            genresIdSet.add(genreid.get());
        }
        Set<PlatformsEN> platformsIdSet = new HashSet<>();
        for (String platform : platformsSet) {
            long id = Long.parseLong(platform);
            Optional<PlatformsEN> platformId = Optional.of(platformsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not platform")));
            platformsIdSet.add(platformId.get());
        }
        gamePost.setPlatformsSet(platformsIdSet);
        gamePost.setGenresSet(genresIdSet);
        gamePost.setPosterHorizontal_uls(posterHorizontal);
        gamePost.setPosterVertical_urs(photoVertical);
        return repository.save(gamePost);
    }
    @Cacheable(value = "game_en_id", key = "'game_en_id:'+#id")
    public GamePostByIdDTOEN getGamePostDTOEN(String id) {
        GamePostEN gamePostEN = repository.findByUrlPost(id).orElseThrow(()-> new EntityNotFoundException("Not found id Game"));
        GamePostByIdDTOEN gamePostDTOEN = convertToGamePostByIdDTO(gamePostEN);
        return gamePostDTOEN;
    }

    public GamePostByIdDTOEN convertToGamePostByIdDTO(GamePostEN gamePost) {
        GamePostByIdDTOEN gamePostDTOEN = modelMapper.map(gamePost, GamePostByIdDTOEN.class);

        return gamePostDTOEN;
    }

    GamePostEN convertToGamePost(GamePostDTOEN gamePostDTO) {
        return modelMapper.map(gamePostDTO, GamePostEN.class);
    }
    GamePostDTOEN convertToGamePostDTO(GamePostEN gamePost) {
        return modelMapper.map(gamePost, GamePostDTOEN.class);
    }



}


