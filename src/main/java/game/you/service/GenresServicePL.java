package game.you.service;
import game.you.dto.GenresDTOPL;
import game.you.entity.GenresPL;
import game.you.repository.GenresRepositotyPL;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenresServicePL {
    final private GenresRepositotyPL repositoty;
    final private ModelMapper modelMapper;
    public List<GenresDTOPL> getAllGenres() {
        return repositoty.findAll().stream().map(this::convertToGenresDTO).collect(Collectors.toList());
    }

    @Transactional
    public GenresDTOPL addGenre(GenresDTOPL genresDTOPL) {
        GenresPL genre = convertToGenres(genresDTOPL);
        repositoty.save(genre);
        return genresDTOPL;
    }

    GenresPL convertToGenres(GenresDTOPL genresDTO) {
        return modelMapper.map(genresDTO, GenresPL.class);
    }
    GenresDTOPL convertToGenresDTO (GenresPL genres) {
        return modelMapper.map(genres, GenresDTOPL.class);
    }
}
