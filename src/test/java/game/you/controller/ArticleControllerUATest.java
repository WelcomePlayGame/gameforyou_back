package game.you.controller;

import game.you.dto.ArticleDTOUA;
import game.you.entity.ArticleUA;
import game.you.service.ArticleServiceUA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerUATest {
    @InjectMocks
    private ArticleControllerUA controllerUA;

    @Mock
    private ArticleServiceUA serviceUA;
    @Mock
    private ArticleUA articleUA;
    @Mock
    ArticleDTOUA articleDTOUA;
    @Mock
    List<Long> ids;
    @Test
    public void shouldReturn200ForGetAllArticles() {

        Long id = 1L;
        List<ArticleDTOUA> expectedList = Arrays.asList(new ArticleDTOUA(), new ArticleDTOUA());
        when(serviceUA.getListArticle(id)).thenReturn(expectedList);
        ResponseEntity<List<ArticleDTOUA>> response = controllerUA.getListArticle(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedList, response.getBody());
        verify(serviceUA).getListArticle(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCheckAddArticle() throws IOException {
        List<MultipartFile> posterPhoto = Arrays.asList(null, null);
        List<String> tagSet = Arrays.asList("","");
        when(serviceUA.addArticle(articleUA, posterPhoto, ids, tagSet)).thenReturn(articleDTOUA);
        ResponseEntity<ArticleDTOUA> response = controllerUA.addArticle(articleUA, posterPhoto, ids, tagSet);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(serviceUA).addArticle(articleUA, posterPhoto,ids,tagSet);

    }

    @Test
    public void shouldCheckDeleteArticleById() {
        when(articleDTOUA.getId()).thenReturn(1l);
        ResponseEntity<ArticleDTOUA> response = controllerUA.deleteArticle(articleDTOUA.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(serviceUA).deleteArticle(articleDTOUA.getId());
    }
}
