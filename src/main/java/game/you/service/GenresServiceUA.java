package game.you.service;

import game.you.dto.GenresDTOUA;
import game.you.entity.GenresUA;
import game.you.repository.GenresRepositotyUA;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenresServiceUA {
    final private GenresRepositotyUA repositoty;
    final private ModelMapper modelMapper;
    public List<GenresDTOUA> getAllGenres() {
        return repositoty.findAll().stream().map(this::convertToGenresDTO).collect(Collectors.toList());
    }

    @Transactional
    public GenresUA addGenre(GenresUA genre) {
        return repositoty.save(genre);
    }

    GenresUA convertToGenres(GenresDTOUA genresDTO) {
        return modelMapper.map(genresDTO, GenresUA.class);
    }
    GenresDTOUA convertToGenresDTO (GenresUA genres) {
        return modelMapper.map(genres, GenresDTOUA.class);
    }
}
