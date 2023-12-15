package game.you.service;

import game.you.dto.GamePostDTOUA;
import game.you.entity.*;
import game.you.repository.GamePostDesUrlRepositoryUA;
import game.you.repository.GamePostRepositoryUA;
import game.you.repository.GenresRepositotyUA;
import game.you.repository.PlatformsRepositoryUA;
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
public class GamePostServiceUA implements ForkWithFile {
    final  private GamePostRepositoryUA repository;
    final private GamePostDesUrlRepositoryUA repository_des;
    final  private ModelMapper modelMapper;
    final  private PlatformsRepositoryUA platformsRepository;
    final private GenresRepositotyUA genresRepositoty;
    final  private HttpServletRequest request;
    @Value("${server.url.gamepost_poster}")
    private String URL_CATALOG;
    @Value("${file.upload.gamepost_poster}")
    private String UPLOAD_CATALOG;
    @Value("${base_url}")
    private String BASE_URL;
    GamePostUA convertToGamePost(GamePostDTOUA gamePostDTO) {
        return modelMapper.map(gamePostDTO, GamePostUA.class);
    }
    GamePostDTOUA convertToGamePostDTO(GamePostUA gamePost) {
        return modelMapper.map(gamePost, GamePostDTOUA.class);
    }
    public List <GamePostDTOUA> getAllFGame() {
        return repository.findAll().stream().map(this::convertToGamePostDTO).collect(Collectors.toList());
    }

    @Transactional
    public GamePostUA addGamePost(GamePostUA gamePost,
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
        GamePosterHorizontalUA posterHorizontal = new GamePosterHorizontalUA();
        for(MultipartFile file : posterPhoto) {
            String name = generateNameFile(file);
            createDirectory(UPLOAD_CATALOG+"/ua/"+latinTitle, name, file);
             if (file.getOriginalFilename().contains("1024x768") ) {
                posterHorizontal.setPoster_1024x768(BASE_URL+URL_CATALOG+"/ua/"+latinTitle+"/"+name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                posterHorizontal.setPoster_480x320(BASE_URL+URL_CATALOG+"/ua/"+latinTitle+"/"+name);
            }
        }
        Set<GamePost_des_urlsUA> list = new HashSet<>();
        for (Long id : ids) {
        Optional<GamePost_des_urlsUA> game_des = Optional.ofNullable(repository_des.findById(id).orElseThrow(() -> new EntityNotFoundException("No id GamePost_Id for Des")));
        game_des.get().setGamePost(gamePost);
        list.add(game_des.get());
        }
        gamePost.setGamePost_des_urls(list);
        GamePosterVerticalUA photoVertical = new GamePosterVerticalUA();
        String name = generateNameFile(photo);
        createDirectory(UPLOAD_CATALOG+"/ua/"+latinTitle, name, photo);
        photoVertical.setPoster_300x300(BASE_URL+URL_CATALOG+"/ua/"+latinTitle+"/"+name);
        Set<GenresUA> genresIdSet = new HashSet<>();
        for (String genre : genresSet) {
            long id = Long.parseLong(genre);
            Optional<GenresUA> genreid = Optional.of(genresRepositoty.findById(id).orElseThrow(()->new EntityNotFoundException("Not genre")));
            genresIdSet.add(genreid.get());
        }
        Set<PlatformsUA> platformsIdSet = new HashSet<>();
        for (String platform : platformsSet) {
            long id = Long.parseLong(platform);
            Optional<PlatformsUA> platformId = Optional.of(platformsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not platform")));
            platformsIdSet.add(platformId.get());
        }
        gamePost.setPlatformsSet(platformsIdSet);
        gamePost.setGenresSet(genresIdSet);
        gamePost.setPosterHorizontal_uls(posterHorizontal);
        gamePost.setPosterVertical_urs(photoVertical);
        return repository.save(gamePost);
    }


    public GamePostDTOUA getPostById(String id) {
        Optional<GamePostUA> gamePostUA = repository.findByUrl(id);
        GamePostDTOUA gamePostDTOUA = convertToGamePostDTO(gamePostUA.get());
        return  gamePostDTOUA;
    }
}


