package game.you.service;


import game.you.dto.ArticleDTORU;
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
public class ArticleServiceRU implements ForkWithFile {
    final private ArticleRepositoryRU repository;
    final private HttpServletRequest request;
    final private CategoryRepositoryRU repository_ca;
    final private ArticleDesUrlsRepositoryRU repository_des;
    final private TagRepositoryRU repository_tag;
    final private GamePostRepositoryRU repository_game;
    final  private ModelMapper modelMapper;
    @Value("${base_url}")
    private String BASE_URL;
    @Value("${server.url.articlies_poster}")
    private String URL_ARTICLES_POSTER;
    @Value("${file.upload.articlies_poster}")
     private String PATH_FILE_POSTER;
    @Transactional
    public ArticleDTORU addArticle(ArticleRU articleRU, List< MultipartFile> posterPhoto , List<Long> ids, List<String> tagSet) throws IOException {

        String latinTitle = StringUtils.stripAccents(articleRU.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        Optional<CategoryRU> categoryRU = Optional.ofNullable(repository_ca.findById(articleRU.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("No id")));
        articleRU.setCategory(categoryRU.get());


        Article_poster_urlsRU poster_urlsRU = new Article_poster_urlsRU();
        for (MultipartFile file : posterPhoto) {
            String name = generateNameFile(file);
            createDirectory(PATH_FILE_POSTER + "/ru/" + latinTitle, name, file);

            if (file.getOriginalFilename().contains("1024x768")) {
                poster_urlsRU.setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/ru/" + latinTitle + "/" + name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                poster_urlsRU.setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/ru/" + latinTitle + "/" + name);
            }
        }

        Set<Article_des_urlsRU> listDes = new HashSet<>();
        for (Long id : ids) {
            Article_des_urlsRU des = repository_des.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No id"));
            des.setArticle(articleRU);
            listDes.add(des);
        }


        Set<TagRU> listTag = new HashSet<>();
        for (String t : tagSet) {
            long id = Long.parseLong(t);
            TagRU tag = repository_tag.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not id"));
            listTag.add(tag);
        }
        StatisticsArticleRU statisticsArticleRU = new StatisticsArticleRU();
        Optional<GamePostRU> gamePostENOptional = repository_game.findById(articleRU.getId());

        if (gamePostENOptional.isPresent()) {
            GamePostRU gamePostRU = gamePostENOptional.get();

            // Проверка на пустую строку (замените "" на значение, которое считаете пустой строкой)
            if (gamePostRU.getId().equals("")) {
                articleRU.setGamePost(null);
            } else {
                articleRU.setGamePost(gamePostRU);
            }
        } else {
            articleRU.setGamePost(null);
        }
        articleRU.setStatistics(statisticsArticleRU);
        articleRU.setPosterUrls(poster_urlsRU);
        articleRU.setTagSet(listTag);
        articleRU.setArticle_des_urls(listDes);
        articleRU.setAtCreate(Instant.now());
        repository.save(articleRU);

        ArticleDTORU articleDTORU = covertToArticleDTORU(articleRU);
        return articleDTORU;
    }

    public List<ArticleDTORU> getListArticle() {
        return  repository.findAll().stream().map(this::covertToArticleDTORU).collect(Collectors.toList());
    }

    ArticleRU convertToArticleRU (ArticleDTORU articleDTORU) {
        return  modelMapper.map(articleDTORU, ArticleRU.class);
    }

    ArticleDTORU covertToArticleDTORU (ArticleRU articleRU) {
        return  modelMapper.map(articleRU, ArticleDTORU.class);
    }

    public ArticleDTORU getArticleById(String id) {
        ArticleRU articleRU = repository.findByUrl(id).orElseThrow(()-> new EntityNotFoundException("No id"));
        ArticleDTORU articleDTORU = covertToArticleDTORU(articleRU);
        return articleDTORU;
    }
}
