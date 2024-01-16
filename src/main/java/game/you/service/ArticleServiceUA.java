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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    final private CategoryRepositoryUA repository_ca;
    final private ArticleDesUrlsRepositoryUA repository_des;
    final private TagRepositoryUA repository_tag;
    final private GamePostRepositoryUA repository_game;
    final private ArticlePosterRepositoryUA repository_poster;
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

        if (articleUA.getGamePost()!=null) {
            Long id = articleUA.getGamePost().getId();
            Optional<GamePostUA> gamePostENOptional;
            if (id != null) {
                gamePostENOptional = repository_game.findById(id);
                articleUA.setGamePost(gamePostENOptional.get());
            } else {
                articleUA.setGamePost(null);
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
    @Cacheable(value = "article_ua_all", key = "'article_ua_all'+#id")
    public List<ArticleDTOUA> getListArticle(Long id) {

        return  repository.findAllCustom(id).stream().map(this::covertToArticleDTOUA).collect(Collectors.toList());
    }
    @Cacheable(value = "article_ua_id", key = "'article_ua_id:'+#id")
    public ArticleDTOUA getArticleById(String id) {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("String id not be null for method getArticleById");
        }
        ArticleUA articleUA = repository.findByUrl(id).orElseThrow(()-> new EntityNotFoundException("No id"));
        ArticleDTOUA articleDTOUA = covertToArticleDTOUA(articleUA);
        return articleDTOUA;
    }


    @Transactional
    public ArticleDTOUA updateArticle(ArticleUA articleUA, List<MultipartFile> posterPhoto, List<Long> ids, List<String> tagSet) throws IOException {
        ArticleUA articleUAupdate = repository.findById(articleUA.getId()).orElseThrow(()-> new EntityNotFoundException("no id for article"));

        if(articleUA.getTitle() != null) {
            articleUAupdate.setTitle(articleUA.getTitle());
        }
        Optional<Article_poster_urlsUA> poster_urlsUA = Optional.ofNullable(repository_poster.findById(articleUA.getId()).orElseThrow(() -> new EntityNotFoundException("no id poster")));

        String latinTitle = StringUtils.stripAccents(articleUAupdate.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        if (!posterPhoto.isEmpty()) {
            for (MultipartFile file : posterPhoto) {
                String name = generateNameFile(file);
                createDirectory(PATH_FILE_POSTER + "/ua/" + latinTitle, name, file);

                if (file.getOriginalFilename().contains("1024x768")) {
                    poster_urlsUA.get().setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/ua/" + latinTitle + "/" + name);
                } else if (file.getOriginalFilename().contains("480x320")) {
                    poster_urlsUA.get().setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/ua/" + latinTitle + "/" + name);
                }
            }
        }
        Set<Article_des_urlsUA> listDes = new HashSet<>();
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                Article_des_urlsUA des = repository_des.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No id"));
                des.setArticle(articleUAupdate);
                listDes.add(des);
            }
        }
        if(!tagSet.isEmpty()) {
            Set<TagUA> listTag = new HashSet<>();
            for (String t : tagSet) {
                long id = Long.parseLong(t);
                TagUA tag = repository_tag.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Not id"));
                listTag.add(tag);
            }
            articleUAupdate.setTagSet(listTag);
        }
        if (articleUA.getDes() != null) {
            articleUAupdate.setDes(articleUA.getDes());
        }
        if(articleUA.getSeo_title() != null) {
            articleUAupdate.setSeo_title(articleUA.getSeo_title());
        }
        if (articleUA.getSeo_des() != null) {
            articleUAupdate.setSeo_des(articleUA.getSeo_des());
        }
        if (articleUA.getCategory() != null) {
            articleUAupdate.setCategory(articleUA.getCategory());
        }
        articleUAupdate.setPosterUrls(poster_urlsUA.get());
        articleUAupdate.setArticle_des_urls(listDes);
        repository.save(articleUAupdate);
        return covertToArticleDTOUA(articleUAupdate);
    }
    ArticleUA convertToArticleUA (ArticleDTOUA articleDTOUA) {return  modelMapper.map(articleDTOUA, ArticleUA.class);
    }

    ArticleDTOUA covertToArticleDTOUA (ArticleUA articleUA) {
        return  modelMapper.map(articleUA, ArticleDTOUA.class);
    }

    @Transactional
    public void deleteArticle(long id) {
        Optional<Article_poster_urlsUA> poster_urlsUA = Optional.ofNullable(repository_poster.findById(id).orElseThrow(() -> new EntityNotFoundException("no id poster")));
        String url_1024 = poster_urlsUA.get().getPosterUrl1024x768();
        String url_480 = poster_urlsUA.get().getPosterUrl480x320();
        String name_1024 = url_1024.substring(url_1024.lastIndexOf('/')+1);
        String name_480 = url_480.substring(url_480.lastIndexOf('/')+1);
        Path path_1024 = Paths.get(PATH_FILE_POSTER+"/ua/", name_1024);
        Path path_480 = Paths.get(PATH_FILE_POSTER+"/ua/", name_480);
        deleteDirectory(path_1024);
        deleteDirectory(path_480);
        repository.deleteById(id);
    }
}
