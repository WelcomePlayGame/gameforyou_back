package game.you.service;

import game.you.dto.ArticleDTORU;
import game.you.dto.ArticleDTOUA;
import game.you.entity.*;
import game.you.repository.*;
import game.you.unit.ForkWithFile;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceUA implements ForkWithFile {
    final private ArticleRepositoryUA repository;
    final private HttpServletRequest request;
    final private CategoryRepositoryUA repository_ca;
    final private ArticleDesUrlsRepositoryUA repository_des;
    final private TagRepositoryUA repository_tag;
    final private GamePostRepositoryUA repository_game;
    final  private ModelMapper modelMapper;
    @Value("${base_url}")
    private String BASE_URL;
    @Value("${server.url.articlies_poster}")
    private String URL_ARTICLES_POSTER;
    @Value("${file.upload.articlies_poster}")
     private String PATH_FILE_POSTER;
    @Transactional
    public ArticleDTOUA addArticle(ArticleUA articleUA, List< MultipartFile> posterPhoto , List<Long> ids, List<String> tagSet) throws IOException {

        String latinTitle = StringUtils.stripAccents(articleUA.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        Optional<CategoryUA> categoryUA = Optional.ofNullable(repository_ca.findById(articleUA.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("No id")));
        articleUA.setCategory(categoryUA.get());


        Article_poster_urlsUA poster_urlsUA = new Article_poster_urlsUA();
        for (MultipartFile file : posterPhoto) {
            String name = generateNameFile(file);
            createDirectory(PATH_FILE_POSTER + "/ua/" + latinTitle, name, file);

            if (file.getOriginalFilename().contains("1024x768")) {
                poster_urlsUA.setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/ua/" + latinTitle + "/" + name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                poster_urlsUA.setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/ua/" + latinTitle + "/" + name);
            }
        }

        Set<Article_des_urlsUA> listDes = new HashSet<>();
        for (Long id : ids) {
            Article_des_urlsUA des = repository_des.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No id"));
            des.setArticle(articleUA);
            listDes.add(des);
        }


        Set<TagUA> listTag = new HashSet<>();
        for (String t : tagSet) {
            long id = Long.parseLong(t);
            TagUA tag = repository_tag.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not id"));
            listTag.add(tag);
        }
        StatisticsArticleUA statisticsArticleUA = new StatisticsArticleUA();
        Optional<GamePostUA> gamePostENOptional = repository_game.findById(articleUA.getId());

        if (gamePostENOptional.isPresent()) {
            GamePostUA gamePostUA = gamePostENOptional.get();

            // Проверка на пустую строку (замените "" на значение, которое считаете пустой строкой)
            if (gamePostUA.getId().equals("")) {
                articleUA.setGamePost(null);
            } else {
                articleUA.setGamePost(gamePostUA);
            }
        } else {
            articleUA.setGamePost(null);
        }
        articleUA.setStatistics(statisticsArticleUA);
        articleUA.setPosterUrls(poster_urlsUA);
        articleUA.setTagSet(listTag);
        articleUA.setArticle_des_urls(listDes);
        articleUA.setAtCreate(Instant.now());
        repository.save(articleUA);

        ArticleDTOUA articleDTOUA = covertToArticleDTOUA(articleUA);
        return articleDTOUA;
    }
    public List<ArticleDTOUA> getListArticle() {
        return  repository.findAll().stream().map(this::covertToArticleDTOUA).collect(Collectors.toList());
    }

    public ArticleDTOUA getArticleById(String id) {
        ArticleUA articleUA = repository.findByUrl(id).orElseThrow(()-> new EntityNotFoundException("No id"));
        ArticleDTOUA articleDTOUA = covertToArticleDTOUA(articleUA);
        return articleDTOUA;
    }
    ArticleUA convertToArticleUA (ArticleDTOUA articleDTOUA) {return  modelMapper.map(articleDTOUA, ArticleUA.class);
    }

    ArticleDTOUA covertToArticleDTOUA (ArticleUA articleUA) {
        return  modelMapper.map(articleUA, ArticleDTOUA.class);
    }
}
