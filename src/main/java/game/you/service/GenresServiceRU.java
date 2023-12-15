package game.you.service;

import game.you.dto.GenresDTORU;
import game.you.entity.GenresRU;
import game.you.repository.GenresRepositoryRU;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenresServiceRU {
    final private  GenresRepositoryRU repository;

    final private ModelMapper modelMapper;

    public List<GenresDTORU> getAllGenres() {
        return repository.findAll().stream().map(this::convertToGenresDTO).collect(Collectors.toList());
    }

    @Transactional
    public GenresDTORU addGenre(GenresDTORU genresDTORU) {
        GenresRU genre = convertToGenres(genresDTORU);
        repository.save(genre);
        return genresDTORU;
    }

    GenresRU convertToGenres(GenresDTORU genresDTO) {
        return modelMapper.map(genresDTO, GenresRU.class);
    }
    GenresDTORU convertToGenresDTO (GenresRU genres) {
        return modelMapper.map(genres, GenresDTORU.class);
    }
}
