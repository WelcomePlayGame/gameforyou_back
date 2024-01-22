package game.you.service;

import game.you.dto.ArticleDTOEN;
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
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceEN implements ForkWithFile {
    final private ArticleRepositoryEN repository;
    final private ModelMapper modelMapper;
    final private ArticleDesUrlsRepositoryEN repository_des;
    final private TagRepositoryEN repository_tag;
    final private CategoryRepositoryEN repository_ca;
    final private GamePostRepositoryEN repository_game;
    final private ArticlePosterRepositoryEN repository_poster;
    final private CategoryRepositoryEN categoryRepository_en;
    @Value("${server.url.articlies_poster}")
    private String URL_ARTICLES_POSTER;
    @Value("${base_url}")
    private String BASE_URL;
    @Value("${file.upload.articlies_poster}")
    private String PATH_FILE_POSTER;
    ArticleEN convertToArticleEN(ArticleDTOEN articleDTOEN) {
        return modelMapper.map(articleDTOEN, ArticleEN.class);
    }

    ArticleDTOEN covertToArticleDTOEN(ArticleEN articleEN) {
        return modelMapper.map(articleEN, ArticleDTOEN.class);
    }

    @Transactional
    public ArticleDTOEN addArticle(ArticleEN articleEN, List<MultipartFile> posterPhoto, List<Long> ids, List<String> tagSet)
            throws IOException {
        String latinTitle = StringUtils.stripAccents(articleEN.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        Optional<CategoryEN> categoryEN = Optional.ofNullable(repository_ca.findById(articleEN.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("No id category")));
        articleEN.setCategory(categoryEN.get());


        Article_poster_urlsEN poster_urlsEN = new Article_poster_urlsEN();
        for (MultipartFile file : posterPhoto) {
            String name = generateNameFile(file);
            createDirectory(PATH_FILE_POSTER + "/en/" + latinTitle, name, file);

            if (file.getOriginalFilename().contains("1024x768")) {
                poster_urlsEN.setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/en/" + latinTitle + "/" + name);
            } else if (file.getOriginalFilename().contains("480x320")) {
                poster_urlsEN.setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/en/" + latinTitle + "/" + name);
            }
        }

        Set<Article_des_urlsEN> listDes = new HashSet<>();
        for (Long id : ids) {
            Article_des_urlsEN des = repository_des.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No id"));
            des.setArticle(articleEN);
            listDes.add(des);
        }


        Set<TagEN> listTag = new HashSet<>();
        for (String t : tagSet) {
            long id = Long.parseLong(t);
            TagEN tag = repository_tag.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Not id tag"));
            listTag.add(tag);
        }
        StatisticsArticleEN statisticsArticleEN = new StatisticsArticleEN();

    if (articleEN.getGamePost() !=null) {
        Long id = articleEN.getGamePost().getId();
        Optional<GamePostEN> gamePostENOptional;
        if (id != null) {
            gamePostENOptional = repository_game.findById(id);
            articleEN.setGamePost(gamePostENOptional.get());
        } else {
            articleEN.setGamePost(null);
        }
    }

        articleEN.setStatistics(statisticsArticleEN);
        articleEN.setPosterUrls(poster_urlsEN);
        articleEN.setTagSet(listTag);
        articleEN.setArticle_des_urls(listDes);
        articleEN.setAtCreate(Instant.now());
        repository.save(articleEN);

        ArticleDTOEN articleDTOEN = covertToArticleDTOEN(articleEN);
        return articleDTOEN;
    }
    @Transactional
    public ArticleDTOEN updateArticle(ArticleEN articleEN, List<MultipartFile> posterPhoto, List<Long> ids, List<String> tagSet) throws IOException, URISyntaxException {
        ArticleEN articleENupdate = repository.findById(articleEN.getId()).orElseThrow(() -> new EntityNotFoundException("no id for article"));
        URI cutPath = new URI(articleENupdate.getPosterUrls().getPosterUrl1024x768());
        String path = cutPath.getPath();
        log.info(articleEN.getCategory().toString());
        Path pathForDelete = Path.of(path).getParent();
        ForkWithFile.deleteDirectoryAndItsContent(pathForDelete);

        if(articleEN.getTitle() != null) {
            articleENupdate.setTitle(articleEN.getTitle());
        }
        Optional<Article_poster_urlsEN> poster_urlsEN = Optional.ofNullable(repository_poster.findById(articleENupdate.getId()).orElseThrow(() -> new EntityNotFoundException("no id poster")));

        String latinTitle = StringUtils.stripAccents(articleENupdate.getTitle())
                .replaceAll("\\s", "_")
                .replaceAll("[^\\p{L}\\p{N}]", "")
                .toLowerCase();
        if (!posterPhoto.isEmpty()) {
            for (MultipartFile file : posterPhoto) {
                String name = generateNameFile(file);
                createDirectory(PATH_FILE_POSTER + "/en/" + latinTitle, name, file);

                if (file.getOriginalFilename().contains("1024x768")) {
                    poster_urlsEN.get().setPosterUrl1024x768(BASE_URL+URL_ARTICLES_POSTER + "/en/" + latinTitle + "/" + name);
                } else if (file.getOriginalFilename().contains("480x320")) {
                    poster_urlsEN.get().setPosterUrl480x320(BASE_URL+URL_ARTICLES_POSTER + "/en/" + latinTitle + "/" + name);
                }
            }
        }
        Set<Article_des_urlsEN> listDes = new HashSet<>();
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                Article_des_urlsEN des = repository_des.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("No id"));
                des.setArticle(articleENupdate);
                listDes.add(des);
            }
        }
        if(!tagSet.isEmpty()) {
            Set<TagEN> listTag = new HashSet<>();
            for (String t : tagSet) {
                long id = Long.parseLong(t);
                TagEN tag = repository_tag.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Not id"));
                listTag.add(tag);
            }
            articleENupdate.setTagSet(listTag);
        }
        if (articleEN.getDes() != null) {
            articleENupdate.setDes(articleEN.getDes());
        }
        if(articleEN.getSeo_title() != null) {
            articleENupdate.setSeo_title(articleEN.getSeo_title());
        }
        if (articleEN.getSeo_des() != null) {
            articleENupdate.setSeo_des(articleEN.getSeo_des());
        }
        if (articleEN.getCategory() != null) {
            CategoryEN categoryEN = categoryRepository_en.findById(articleEN.getId()).orElseThrow(()-> new EntityNotFoundException("Category id no found"));
            articleENupdate.setCategory(categoryEN);
//            repository_ca.save(categoryEN);
        }
        articleENupdate.setPosterUrls(poster_urlsEN.get());
        articleENupdate.setArticle_des_urls(listDes);

        repository.save(articleENupdate);
        return covertToArticleDTOEN(articleENupdate);
    }

    @Cacheable(value = "article_en_all", key = "'article_en_all'+#id")
    public List<ArticleDTOEN> getListArticle(Long id) {
        return repository.findAllCustom(id).stream().map(this::covertToArticleDTOEN).collect(Collectors.toList());
    }

    @Cacheable(value = "article_en_id", key = "'article_en_id:'+#id")
    public ArticleDTOEN getArticleById(String id) {
        ArticleEN articleEN = repository.findByUrl(id).orElseThrow(()-> new EntityNotFoundException("No id"));
        ArticleDTOEN articleDTOEN = covertToArticleDTOEN(articleEN);
        return articleDTOEN;
    }

    @Transactional
    public void deleteArticle(long id) {
        repository.deleteById(id);
    }
}
