package game.you.service;

import game.you.dto.GamePostDTOEN;
import game.you.entity.*;
import game.you.repository.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GamePostServiceENTest {

    @InjectMocks
    GamePostServiceEN service;
    @Mock
    GamePostRepositoryEN repository;
    @Mock
    GamePostDesUrlRepositoryEN repository_des;
    @Mock
    ModelMapper modelMapper;
    @Mock
    PlatformsRepositoryEN platformsRepository;
    @Mock
    GenresRepositotyEN genresRepositoty;
    @Mock
    GamePosterHorizontalRepositoryEN repository_poster_horizontal;
    @Mock
    GamePosterVerticalRepositoryEN repository_poster_vertical;

    @Test
    public void shouldGetAllGamePost() {
        List<GamePostEN> list = new ArrayList<>();
        when(repository.findAllCustom(1l, "x")).thenReturn(list);
        List<GamePostDTOEN> list_next = service.getAllFGame(1L, "x");
        assertEquals(list_next, list);
    }

//    @Ignore
    @Test
    public void shouldUpdateMethod() throws URISyntaxException, IOException {
        GamePostEN mockGamePost = GamePostEN.builder()
                .id(1L)
                .title("title")
                .seo_title("seo title")
                .seo_des("seo des")
                .des("des")
                .posterHorizontal_uls(mock(GamePosterHorizontalEN.class))
                .posterVertical_urs(mock(GamePosterVerticalEN.class))
                .developer(mock(DeveloperGameEN.class))
                .publisher(mock(PublisherEN.class))
                .articleSet(new LinkedHashSet<>())
                .commentSet(new LinkedHashSet<>())
                .platformsSet(new LinkedHashSet<>())
                .genresSet(new LinkedHashSet<>())
                .gamePost_des_urls(new LinkedHashSet<>())
                .build();
        when(repository.findByUrlPost(any(String.class)))
                .thenReturn(Optional.of(mockGamePost));

        GamePostEN gamePost = new GamePostEN();
        List<MultipartFile> posterPhoto = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        MultipartFile photo = mock(MultipartFile.class);
        List<String> genresSet = new ArrayList<>();
        List<String> platformsSet = new ArrayList<>();

        GamePostEN result = service.updateGame(gamePost, posterPhoto, ids, photo, genresSet, platformsSet);


        assertNotNull(result);
    }
}