package game.you.service;

import game.you.dto.GamePostByIdDTOEN;
import game.you.dto.GamePostDTOEN;
import game.you.entity.*;
import game.you.repository.*;
import game.you.unit.ForkWithFile;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
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
    final private GamePosterHorizontalRepositoryEN repository_poster_horintal;
    final private GamePosterVerticalRepositoryEN repository_poster_vertical;
    @Value("${server.url.gamepost_poster}")
    private String URL_CATALOG;
    @Value("${file.upload.gamepost_poster}")
    private String UPLOAD_CATALOG;
    @Value("${base_url}")
    private String BASE_URL;


    @Cacheable(value = "game_en_all", key = "'game_en_all'+#id")
    public List <GamePostDTOEN> getAllFGame(Long id, String series_games) {
        return repository.findAllCustom(id, series_games).stream().map(this::convertToGamePostDTO).collect(Collectors.toList());
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
    @Transactional
    public GamePostEN updateGame
            (
                    GamePostEN gamePost,
                    List<MultipartFile> posterPhoto,
                    List<Long> ids,
                    MultipartFile photo,
                    List <String> genresSet,
                    List<String> platformsSet
    ) throws URISyntaxException, IOException {
        GamePostEN gamePostUpdate = repository.findById(gamePost.getId()).orElseThrow(()-> new EntityNotFoundException("Not game id"));
        URI cutHorPath = new URI(gamePostUpdate.getPosterHorizontal_uls().getPoster_1024x768());
        URI cutVerPath = new URI(gamePostUpdate.getPosterVertical_urs().getPoster_300x300());

        String pathHorString = cutHorPath.getPath();
        String pathVerString = cutVerPath.getPath();

        Path pathHor = Path.of(pathHorString).getParent();
        ForkWithFile.deleteDirectoryAndItsContent(pathHor);

        if (gamePost.getTitle()!=null) {
            gamePostUpdate.setTitle(gamePost.getTitle());
        }
        if (gamePost.getSeo_title()!=null) {
            gamePostUpdate.setSeo_title(gamePostUpdate.getSeo_title());
        }
        if (gamePost.getDes()!=null) {
            gamePostUpdate.setDes(gamePost.getDes());
        }
        if (gamePost.getSeo_des()!=null) {
            gamePostUpdate.setSeo_des(gamePost.getSeo_des());
        }
        if (gamePost.getOs() != null) {
            gamePostUpdate.setOs(gamePost.getOs());
        }
        if (gamePost.getMinProcessor() != null) {
            gamePostUpdate.setMinProcessor(gamePost.getMinProcessor());
        }
        if (gamePost.getMaxProcessor()!=null) {
            gamePostUpdate.setMaxProcessor(gamePost.getMaxProcessor());
        }
        if (gamePost.getMinRam() != null) {
            gamePostUpdate.setMinRam(gamePost.getMinRam());
        }
        if (gamePost.getMaxRam() != null) {
            gamePostUpdate.setMaxRam(gamePost.getMaxRam());
        }
        if (gamePost.getDirectX()!=null) {
            gamePostUpdate.setDirectX(gamePost.getDirectX());
        }
        if(gamePost.getLan() !=null) {
            gamePostUpdate.setOs(gamePost.getLan());
        }
        if (gamePost.getMemory()!=null) {
            gamePostUpdate.setMemory(gamePost.getMemory());
        }
        if (gamePost.getSeries_games()!=null) {
            gamePostUpdate.setSeries_games(gamePost.getSeries_games());
        }

        Optional<GamePosterHorizontalEN> gamePosterHorizontalEN = Optional.ofNullable(repository_poster_horintal.findById(gamePost.getId()).orElseThrow(() -> new EntityNotFoundException("No id Photo Horizontal")));
        Optional<GamePosterVerticalEN> gamePosterVerticalEN = Optional.ofNullable(repository_poster_vertical.findById(gamePost.getId()).orElseThrow(() -> new EntityNotFoundException("No id Vertical Photo")));

        String latinTitle = StringUtils.stripAccents(gamePostUpdate.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        if (!posterPhoto.isEmpty()) {
            for (MultipartFile file : posterPhoto) {
                String name = generateNameFile(file);
                createDirectory(UPLOAD_CATALOG+"/en/"+latinTitle, name, file);
            if (file.getOriginalFilename().contains("1024x768")) {
                gamePosterHorizontalEN.get().setPoster_1024x768(BASE_URL+URL_CATALOG+"/en/"+latinTitle+"/"+name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                gamePosterHorizontalEN.get().setPoster_480x320(BASE_URL+URL_CATALOG+"/en/"+latinTitle+"/"+name);
            }
            }
        if (!photo.isEmpty()) {
            String name = generateNameFile(photo);
            createDirectory(UPLOAD_CATALOG+"/en/"+latinTitle, name, photo);
            gamePosterVerticalEN.get().setPoster_300x300(BASE_URL+URL_CATALOG+"/en/"+latinTitle+"/"+name);
        }
        }
        Set<GenresEN> genresUpdateSet = new HashSet<>();
        for(String id : genresSet) {
            Long idGenre = Long.parseLong(id);
            GenresEN genre = genresRepositoty.findById(idGenre).orElseThrow(()-> new EntityNotFoundException("No Id Genre"));
            genresUpdateSet.add(genre);
        }
        Set<PlatformsEN> platformUpdateSet = new HashSet<>();
        for(String id : platformsSet){
            Long idPlatform = Long.parseLong(id);
            PlatformsEN platforms = platformsRepository.findById(idPlatform).orElseThrow(()-> new EntityNotFoundException("No id Platform"));
            platformUpdateSet.add(platforms);
        }
        Set<GamePost_des_urlsEN> list = new HashSet<>();
        for (Long id : ids) {
            GamePost_des_urlsEN game_des = repository_des.findById(id).orElseThrow(()-> new EntityNotFoundException("No id photo_des"));
            list.add(game_des);
        }
        gamePostUpdate.setPlatformsSet(platformUpdateSet);
        gamePostUpdate.setGenresSet(genresUpdateSet);
        gamePostUpdate.setGamePost_des_urls(list);
        repository.save(gamePostUpdate);
        return gamePostUpdate;
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


    @Transactional
    public void deleteGame(long id) {
        repository.deleteById(id);
    }
}


