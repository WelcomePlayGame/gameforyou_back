package game.you.service;


import game.you.entity.Article_des_urlsEN;
import game.you.repository.ArticleDesUrlsRepositoryEN;
import game.you.unit.ForkWithFile;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ArticleDesUrlsServiceEN implements ForkWithFile {

    final private ArticleDesUrlsRepositoryEN repository;
    @Value("${server.url.articlies_des}")
    private String URL_CATALOG;
    @Value("${file.upload.articlies_des}")
     private String UPLOAD_CATALOG;
    @Value("${base_url}")
    private String BASE_URL;
    public List<Article_des_urlsEN> getListUrl() {
        return  repository.findAll();
    }
    @Transactional
    public Article_des_urlsEN addUrl(MultipartFile photo) throws IOException {

    String namePhoto = generateNameFile(photo);
    log.info(UPLOAD_CATALOG);
    createDirectory(UPLOAD_CATALOG+"/en/", namePhoto, photo);
    Article_des_urlsEN article_des_urls = new Article_des_urlsEN();
    article_des_urls.setUrl(BASE_URL+URL_CATALOG+"/en/"+namePhoto);
    return repository.save(article_des_urls);
    }


    @Transactional
    public void deleteUrl (long id) {
        Article_des_urlsEN article_des_urls  = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Url..sry man"));
        String fileUrl = article_des_urls.getUrl();
        String nameFile = fileUrl.substring(fileUrl.lastIndexOf('/')+1);
        Path path = Paths.get(UPLOAD_CATALOG+"/en/", nameFile);
        deleteDirectory(path);
        repository.deleteById(article_des_urls.getId());
    }
}
