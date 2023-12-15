package game.you.service;


import game.you.entity.GamePost_des_urlsUA;
import game.you.repository.GamePostDesUrlRepositoryUA;
import game.you.unit.ForkWithFile;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class GamePostDesUrlServiceUA implements ForkWithFile {

    final  private HttpServletRequest request;
    @Value("${server.url.gamepost_des}")
    private String URL_CATALOG;
    @Value("${file.upload.gamepost_des}")
    private String UPLOAD_CATALOG;
    @Value("${base_url}")
    private String BASE_URL;
    final private GamePostDesUrlRepositoryUA repository;

    public List<GamePost_des_urlsUA> getListDesurl() {
        return repository.findAll();
    }

    @Transactional
    public GamePost_des_urlsUA addGamePostDesUrl(MultipartFile photo) throws IOException {
        String namePhoto = generateNameFile(photo);
        log.info(UPLOAD_CATALOG);
        createDirectory(UPLOAD_CATALOG, namePhoto, photo);
        GamePost_des_urlsUA gamePost_des_urls =  new GamePost_des_urlsUA();
        gamePost_des_urls.setUrl(BASE_URL+URL_CATALOG+"/"+namePhoto);
        return repository.save(gamePost_des_urls);
    }

    @Transactional
    public void deleteGamePostDesUrls(Long id) {
       repository.deleteById(id);
    }
}