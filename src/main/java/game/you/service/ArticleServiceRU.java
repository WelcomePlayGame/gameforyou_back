package game.you.service;


import game.you.dto.ArticleDTOEN;
import game.you.dto.ArticleDTOPL;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
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
    final private ArticlePosterRepositoryRU repository_poster;
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
                .orElseThrow(() -> new EntityNotFoundException("No id category")));
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
                    .orElseThrow(() -> new EntityNotFoundException("No id des"));
            des.setArticle(articleRU);
            listDes.add(des);
        }


        Set<TagRU> listTag = new HashSet<>();
        for (String t : tagSet) {
            long id = Long.parseLong(t);
            TagRU tag = repository_tag.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not id tag"));
            listTag.add(tag);
        }
        StatisticsArticleRU statisticsArticleRU = new StatisticsArticleRU();
//        if (articleRU.getGamePost()!=null) {
//            Long id = articleRU.getGamePost().getId();
//            Optional<GamePostRU> gamePostENOptional;
//            if (id != null) {
//                gamePostENOptional = repository_game.findById(id);
//                articleRU.setGamePost(gamePostENOptional.get());
//            } else {
//                articleRU.setGamePost(null);
//            }
//        } else {
//            articleRU.setGamePost(null);
//        }
        if (articleRU.getGamePost() !=null) {
            Long id = articleRU.getGamePost().getId();
            Optional<GamePostRU> gamePostRUOptional;
            if (id != null) {
                gamePostRUOptional = repository_game.findById(id);
                articleRU.setGamePost(gamePostRUOptional.get());
            } else {
                articleRU.setGamePost(null);
            }
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
    @Transactional
    public ArticleDTORU updateArticle(ArticleRU articleRU, List<MultipartFile> posterPhoto, List<Long> ids, List<String> tagSet) throws IOException, URISyntaxException {
        ArticleRU articleRUupdate = repository.findById(articleRU.getId()).orElseThrow(() -> new EntityNotFoundException("no id for article"));
        URI cutPath = new URI(articleRUupdate.getPosterUrls().getPosterUrl1024x768());
        String path = cutPath.getPath();


        Path pathForDelete = Path.of(path).getParent();
        ForkWithFile.deleteDirectoryAndItsContent(pathForDelete);

        if(articleRU.getTitle() != null) {
            articleRUupdate.setTitle(articleRU.getTitle());
        }
        Optional<Article_poster_urlsRU> poster_urlsRU = Optional.ofNullable(repository_poster.findById(articleRUupdate.getPosterUrls().getId()).orElseThrow(() -> new EntityNotFoundException("no id poster")));

        String latinTitle = StringUtils.stripAccents(articleRUupdate.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        if (!posterPhoto.isEmpty()) {
            for (MultipartFile file : posterPhoto) {
                String name = generateNameFile(file);
                createDirectory(PATH_FILE_POSTER + "/ru/" + latinTitle, name, file);

                if (file.getOriginalFilename().contains("1024x768")) {
                    poster_urlsRU.get().setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/ru/" + latinTitle + "/" + name);
                } else if (file.getOriginalFilename().contains("480x320")) {
                    poster_urlsRU.get().setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/ru/" + latinTitle + "/" + name);
                }
            }
        }
        Set<Article_des_urlsRU> listDes = new HashSet<>();
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                Article_des_urlsRU des = repository_des.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No id"));
                des.setArticle(articleRUupdate);
                listDes.add(des);
            }
        }
        if(!tagSet.isEmpty()) {
            Set<TagRU> listTag = new HashSet<>();
            for (String t : tagSet) {
                long id = Long.parseLong(t);
                TagRU tag = repository_tag.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Not id"));
                listTag.add(tag);
            }
            articleRUupdate.setTagSet(listTag);
        }
        if (articleRU.getDes() != null) {
            articleRUupdate.setDes(articleRU.getDes());
        }
        if(articleRU.getSeo_title() != null) {
            articleRUupdate.setSeo_title(articleRU.getSeo_title());
        }
        if (articleRU.getSeo_des() != null) {
            articleRUupdate.setSeo_des(articleRU.getSeo_des());
        }
        if (articleRU.getCategory() != null) {
            CategoryRU categoryRU = repository_ca.findById(articleRU.getCategory().getId()).orElseThrow(()-> new EntityNotFoundException("Category id no found"));
            articleRUupdate.setCategory(categoryRU);
        }
        if (articleRU.getGamePost() != null) {
            GamePostRU gamePostRU = repository_game.findById(articleRU.getGamePost().getId()).orElseThrow(()-> new EntityNotFoundException("No id game"));
            articleRUupdate.setGamePost(gamePostRU);
        }
        articleRUupdate.setArticle_des_urls(listDes);

        repository.save(articleRUupdate);
        return covertToArticleDTORU(articleRUupdate);
    }
    @Cacheable(value = "article_ru_all", key = "'article_ru_all'+#id")
    public List<ArticleDTORU> getListArticle(Long id) {
        return  repository.findAllCustom(id).stream().map(this::covertToArticleDTORU).collect(Collectors.toList());
    }

    ArticleRU convertToArticleRU (ArticleDTORU articleDTORU) {
        return  modelMapper.map(articleDTORU, ArticleRU.class);
    }

    ArticleDTORU covertToArticleDTORU (ArticleRU articleRU) {
        return  modelMapper.map(articleRU, ArticleDTORU.class);
    }
    @Cacheable(value = "article_ru_id", key = "'article_ru_id:'+#id")
    public ArticleDTORU getArticleById(String id) {
        ArticleRU articleRU = repository.findByUrl(id).orElseThrow(()-> new EntityNotFoundException("No id"));
        ArticleDTORU articleDTORU = covertToArticleDTORU(articleRU);
        return articleDTORU;
    }

    @Transactional
    public void deleteArticle(long id) {
        repository.deleteById(id);
    }
}
