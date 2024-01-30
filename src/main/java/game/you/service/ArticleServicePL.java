package game.you.service;


import game.you.dto.ArticleDTOEN;
import game.you.dto.ArticleDTOPL;
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
public class ArticleServicePL implements ForkWithFile {
    final private ArticleRepositoryPL repository;
    final private ArticlePosterRepositoryPl repository_poster;
    final private CategoryRepositoryPL repository_ca;
    final private ArticleDesUrlsRepositoryPL repository_des;
    final private TagRepositoryPL repository_tag;
    final private GamePostRepositoryPL repository_game;
    final  private ModelMapper modelMapper;
    @Value("${base_url}")
    private String BASE_URL;
    @Value("${server.url.articlies_poster}")
    private String URL_ARTICLES_POSTER;
    @Value("${file.upload.articlies_poster}")
     private String PATH_FILE_POSTER;
    ArticlePL convertToArticlePL (ArticleDTOPL articleDTOPl) {
        return  modelMapper.map(articleDTOPl, ArticlePL.class);
    }

    ArticleDTOPL covertToArticleDTOPL (ArticlePL articlePL) {
        return  modelMapper.map(articlePL, ArticleDTOPL.class);
    }
    @Transactional
    public ArticleDTOPL addArticle(ArticlePL articlePL, List< MultipartFile> posterPhoto , List<Long> ids, List<String> tagSet) throws IOException {

        String latinTitle = StringUtils.stripAccents(articlePL.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        Optional<CategoryPL> categoryPL = Optional.ofNullable(repository_ca.findById(articlePL.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("No id category")));
        articlePL.setCategory(categoryPL.get());


        Article_poster_urlsPL poster_urlsPL = new Article_poster_urlsPL();
        for (MultipartFile file : posterPhoto) {
            String name = generateNameFile(file);
            createDirectory(PATH_FILE_POSTER + "/pl/" + latinTitle, name, file);

            if (file.getOriginalFilename().contains("1024x768")) {
                poster_urlsPL.setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/pl/" + latinTitle + "/" + name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                poster_urlsPL.setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/pl/" + latinTitle + "/" + name);
            }
        }

        Set<Article_des_urlsPL> listDes = new HashSet<>();
        for (Long id : ids) {
            Article_des_urlsPL des = repository_des.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No id des"));
            des.setArticle(articlePL);
            listDes.add(des);
        }


        Set<TagPL> listTag = new HashSet<>();
        for (String t : tagSet) {
            long id = Long.parseLong(t);
            TagPL tag = repository_tag.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not id tag"));
            listTag.add(tag);
        }
        StatisticsArticlePL statisticsArticlePL = new StatisticsArticlePL();

        if (articlePL.getGamePost()!=null) {
            Long id = articlePL.getGamePost().getId();
            Optional<GamePostPL> gamePostENOptional;
            if (id != null) {
                gamePostENOptional = repository_game.findById(id);
                articlePL.setGamePost(gamePostENOptional.get());
            } else {
                articlePL.setGamePost(null);
            }
        } else {
            articlePL.setGamePost(null);
        }
        articlePL.setStatistics(statisticsArticlePL);
        articlePL.setPosterUrls(poster_urlsPL);
        articlePL.setTagSet(listTag);
        articlePL.setArticle_des_urls(listDes);
        articlePL.setAtCreate(Instant.now());
        repository.save(articlePL);

        ArticleDTOPL articleDTOPL = covertToArticleDTOPL(articlePL);
        return articleDTOPL;
    }

    @Transactional
    public ArticleDTOPL updateArticle(ArticlePL articlePL, List<MultipartFile> posterPhoto, List<Long> ids, List<String> tagSet) throws IOException, URISyntaxException {
        ArticlePL articlePLupdate = repository.findById(articlePL.getId()).orElseThrow(() -> new EntityNotFoundException("no id for article"));
        URI cutPath = new URI(articlePLupdate.getPosterUrls().getPosterUrl1024x768());
        String path = cutPath.getPath();


        Path pathForDelete = Path.of(path).getParent();
        ForkWithFile.deleteDirectoryAndItsContent(pathForDelete);

        if(articlePL.getTitle() != null) {
            articlePLupdate.setTitle(articlePL.getTitle());
        }
        Optional<Article_poster_urlsPL> poster_urlsPL = Optional.ofNullable(repository_poster.findById(articlePLupdate.getPosterUrls().getId()).orElseThrow(() -> new EntityNotFoundException("no id poster")));

        String latinTitle = StringUtils.stripAccents(articlePLupdate.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        if (!posterPhoto.isEmpty()) {
            for (MultipartFile file : posterPhoto) {
                String name = generateNameFile(file);
                createDirectory(PATH_FILE_POSTER + "/pl/" + latinTitle, name, file);

                if (file.getOriginalFilename().contains("1024x768")) {
                    poster_urlsPL.get().setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/pl/" + latinTitle + "/" + name);
                } else if (file.getOriginalFilename().contains("480x320")) {
                    poster_urlsPL.get().setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/pl/" + latinTitle + "/" + name);
                }
            }
        }
        Set<Article_des_urlsPL> listDes = new HashSet<>();
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                Article_des_urlsPL des = repository_des.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No id"));
                des.setArticle(articlePLupdate);
                listDes.add(des);
            }
        }
        if(!tagSet.isEmpty()) {
            Set<TagPL> listTag = new HashSet<>();
            for (String t : tagSet) {
                long id = Long.parseLong(t);
                TagPL tag = repository_tag.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Not id"));
                listTag.add(tag);
            }
            articlePLupdate.setTagSet(listTag);
        }
        if (articlePL.getDes() != null) {
            articlePLupdate.setDes(articlePL.getDes());
        }
        if(articlePL.getSeo_title() != null) {
            articlePLupdate.setSeo_title(articlePL.getSeo_title());
        }
        if (articlePL.getSeo_des() != null) {
            articlePLupdate.setSeo_des(articlePL.getSeo_des());
        }
        if (articlePL.getCategory() != null) {
            CategoryPL categoryPL = repository_ca.findById(articlePL.getCategory().getId()).orElseThrow(()-> new EntityNotFoundException("Category id no found"));
            articlePLupdate.setCategory(categoryPL);
        }
        if (articlePL.getGamePost()!=null) {
            GamePostPL gamePostPL = repository_game.findById(articlePL.getGamePost().getId()).orElseThrow(()-> new EntityNotFoundException("Game no id") );
            articlePLupdate.setGamePost(gamePostPL);
        }
        articlePLupdate.setArticle_des_urls(listDes);
        repository.save(articlePLupdate);
        return covertToArticleDTOPL(articlePLupdate);
    }
    @Cacheable(value = "article_pl_all", key = "'article_pl_all'+#id")
    public List<ArticleDTOPL> getListArticle(Long id) {
        return  repository.findAllCustom(id).stream().map(this::covertToArticleDTOPL).collect(Collectors.toList());
    }

    @Cacheable(value = "article_pl_id", key = "'article_pl_id:'+#id")
    public ArticleDTOPL getArticleById(String id) {
        ArticlePL articlePL = repository.findByUrl(id).orElseThrow(()-> new EntityNotFoundException("No id"));
        ArticleDTOPL articleDTOPL = covertToArticleDTOPL(articlePL);
        return articleDTOPL;
    }

    @Transactional
    public void deleteArticle(long id) {
        repository.deleteById(id);
    }
}
