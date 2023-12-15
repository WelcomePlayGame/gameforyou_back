package game.you.service;

import game.you.dto.GenresDTOEN;
import game.you.entity.GenresEN;
import game.you.repository.GenresRepositotyEN;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenresServiceEN {
    final private GenresRepositotyEN repositoty;
    final private ModelMapper modelMapper;
    public List<GenresDTOEN> getAllGenres() {
        return repositoty.findAll().stream().map(this::convertToGenresDTO).collect(Collectors.toList());
    }

    @Transactional
    public GenresDTOEN addGenre(GenresDTOEN genresDTOEN) {
        GenresEN genre = convertToGenres(genresDTOEN);
        repositoty.save(genre);
        return genresDTOEN;
    }

    GenresEN convertToGenres(GenresDTOEN genresDTO) {
        return modelMapper.map(genresDTO, GenresEN.class);
    }
    GenresDTOEN convertToGenresDTO (GenresEN genres) {
        return modelMapper.map(genres, GenresDTOEN.class);
    }
}
