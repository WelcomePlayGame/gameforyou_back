package game.you.service;


import game.you.dto.GamePostDTOPL;
import game.you.entity.*;
import game.you.repository.GamePostDesUrlRepositoryPL;
import game.you.repository.GamePostRepositoryPL;
import game.you.repository.GenresRepositotyPL;
import game.you.repository.PlatformsRepositoryPL;
import game.you.unit.ForkWithFile;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
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
public class GamePostServicePL implements ForkWithFile {
    final  private GamePostRepositoryPL repository;
    final private GamePostDesUrlRepositoryPL repository_des;
    final  private ModelMapper modelMapper;
    final  private PlatformsRepositoryPL platformsRepository;
    final private GenresRepositotyPL genresRepositoty;
    final  private HttpServletRequest request;
    @Value("${server.url.gamepost_poster}")
    private String URL_CATALOG;
    @Value("${file.upload.gamepost_poster}")
    private String UPLOAD_CATALOG;
    @Value("${base_url}")
    private String BASE_URL;

    public List <GamePostDTOPL> getAllFGame() {
        return repository.findAll().stream().map(this::convertToGamePostDTO).collect(Collectors.toList());
    }

    @Transactional
    public GamePostPL addGamePost(GamePostPL gamePost,
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

        GamePosterHorizontalPL posterHorizontal = new GamePosterHorizontalPL();
        for(MultipartFile file : posterPhoto) {
            String name = generateNameFile(file);
            createDirectory(UPLOAD_CATALOG+"/pl/"+latinTitle, name, file);
            if (file.getOriginalFilename().contains("1024x768") ) {
                posterHorizontal.setPoster_1024x768(BASE_URL+URL_CATALOG+"/pl/"+latinTitle+"/"+name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                posterHorizontal.setPoster_480x320(BASE_URL+URL_CATALOG+"/pl/"+latinTitle+"/"+name);
            }
        }
        Set<GamePost_des_urlsPL> list = new HashSet<>();
        for (Long id : ids) {
        Optional<GamePost_des_urlsPL> game_des = Optional.ofNullable(repository_des.findById(id).orElseThrow(() -> new EntityNotFoundException("No id GamePost_Id for Des")));
        game_des.get().setGamePost(gamePost);
        list.add(game_des.get());
        }
        gamePost.setGamePost_des_urls(list);
        GamePosterVerticalPL photoVertical = new GamePosterVerticalPL();
        String name = generateNameFile(photo);
        createDirectory(UPLOAD_CATALOG+"/pl/"+latinTitle, name, photo);
        photoVertical.setPoster_300x300(BASE_URL+URL_CATALOG+"/pl/"+latinTitle+"/"+name);
        Set<GenresPL> genresIdSet = new HashSet<>();
        for (String genre : genresSet) {
            long id = Long.parseLong(genre);
            Optional<GenresPL> genreid = Optional.of(genresRepositoty.findById(id).orElseThrow(()->new EntityNotFoundException("Not genre")));
            genresIdSet.add(genreid.get());
        }
        Set<PlatformsPL> platformsIdSet = new HashSet<>();
        for (String platform : platformsSet) {
            long id = Long.parseLong(platform);
            Optional<PlatformsPL> platformId = Optional.of(platformsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not platform")));
            platformsIdSet.add(platformId.get());
        }
        gamePost.setPlatformsSet(platformsIdSet);
        gamePost.setGenresSet(genresIdSet);
        gamePost.setPosterHorizontal_uls(posterHorizontal);
        gamePost.setPosterVertical_urs(photoVertical);
        return repository.save(gamePost);
    }

    public GamePostDTOPL getGamebyId(String id) {
       GamePostPL game = repository.findByUrl(id).orElseThrow(()-> new EntityNotFoundException("Not found"));
       GamePostDTOPL gamePostDTOPL = convertToGamePostDTO(game);
        return gamePostDTOPL;
    }

    GamePostPL convertToGamePost(GamePostDTOPL gamePostDTO) {
        return modelMapper.map(gamePostDTO, GamePostPL.class);
    }
    GamePostDTOPL convertToGamePostDTO(GamePostPL gamePost) {
        return modelMapper.map(gamePost, GamePostDTOPL.class);
    }
}


