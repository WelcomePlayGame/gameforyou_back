package game.you.service;

import game.you.dto.ArticleDTOUA;
import game.you.entity.ArticleUA;
import game.you.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceUATest {
    @InjectMocks
    ArticleServiceUA articleServiceUA;

    @Mock
    ArticleRepositoryUA repositoryUA;
    @Mock
    CategoryRepositoryUA categoryRepositoryUA;
    @Mock
    ArticleDesUrlsRepositoryUA articleDesUrlsRepositoryUA;
    @Mock
    TagRepositoryUA tagRepositoryUA;
    @Mock
    GamePostRepositoryUA gamePostRepositoryUA;
    @Mock
    ArticlePosterRepositoryUA articlePosterRepositoryUA;
    @Mock
    ModelMapper modelMapper;
    @Mock
    ArticleUA articleUA;

    @Test
    public void shouldRetrieveAllArticlesWhenValidIdProvided() {
        when(articleUA.getId()).thenReturn(1L);
        articleServiceUA.getListArticle(articleUA.getId());
        verify(repositoryUA).findAllCustom(articleUA.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNullIdProvided() {
        articleServiceUA.getListArticle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenUrlNullOrEmpty() {
        String testUrl = "";
        ArticleDTOUA result = articleServiceUA.getArticleById(testUrl);
        verify(repositoryUA, never()).findByUrl(testUrl);
    }
}
