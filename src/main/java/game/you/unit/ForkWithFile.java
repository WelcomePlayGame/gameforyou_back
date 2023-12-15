package game.you.unit;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
public interface ForkWithFile {

    default String generateNameFile(MultipartFile file) {
        return UUID.randomUUID().toString()+"."+file.getOriginalFilename().split("\\.")[1];
    }

    default void createDirectory(String upload, String fileName, MultipartFile file) throws IOException {
        String systemSeparator = FileSystems.getDefault().getSeparator();
        Path dirPhoto = Paths.get(upload.replace("/", systemSeparator));

        if (!Files.exists(dirPhoto)) {
            try {
                Files.createDirectories(dirPhoto);
            } catch (Error | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Catalog already");
        }

        Path photoPath = dirPhoto.resolve(fileName);
        try {
            file.transferTo(photoPath);
        } catch (Error e) {
            throw new RuntimeException(e);
        }
    }


    default void deleteDirectory(Path path) {
        if (Files.exists(path)) {
            try {
                Files.delete(path);
                System.out.println("File delete");
            } catch (Error | IOException e) {
                new Exception(e);
            }
        } else {
            System.out.println("File not found");
        }
    }

    default String getBaseUrl() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request.getRequestURL().toString().replace(request.getRequestURI(), "");
    }
}
