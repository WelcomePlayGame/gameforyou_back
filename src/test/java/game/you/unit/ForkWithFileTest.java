package game.you.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import static org.junit.Assert.*;

public class ForkWithFileTest {
private Path testDirectory;

    public ForkWithFileTest() throws IOException {
    }

    @Before
    public void setUp() throws IOException {
        testDirectory = Files.createTempDirectory("testDir");
    }

    @After
    public void tearDown() throws IOException {
        // Проверяем существование временной директории перед удалением
        if (Files.exists(testDirectory)) {
            Files.walk(testDirectory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    public void testDeleteDirectoryAndItsContent() throws IOException {
        // Создаем несколько файлов и поддиректорий в тестовой директории
        Path file1 = Files.createFile(testDirectory.resolve("file1.txt"));
        Path file2 = Files.createFile(testDirectory.resolve("file2.txt"));
        Path subdir1 = Files.createDirectory(testDirectory.resolve("subdir1"));
        Path file3 = Files.createFile(subdir1.resolve("file3.txt"));
        Path file4 = Files.createFile(subdir1.resolve("file4.txt"));

        // Проверяем, что файлы и директории существуют перед удалением
        assertTrue(Files.exists(file1));
        assertTrue(Files.exists(file2));
        assertTrue(Files.exists(subdir1));
        assertTrue(Files.exists(file3));
        assertTrue(Files.exists(file4));

        // Вызываем метод, который должен удалить директорию и все её содержимое
        ForkWithFile.deleteDirectoryAndItsContent(testDirectory);

        // Проверяем, что файлы и директории больше не существуют после удаления
        assertFalse(Files.exists(file1));
        assertFalse(Files.exists(file2));
        assertFalse(Files.exists(subdir1));
        assertFalse(Files.exists(file3));
        assertFalse(Files.exists(file4));

        // Проверяем, что директория больше не существует
        assertFalse(Files.exists(testDirectory));
    }
}