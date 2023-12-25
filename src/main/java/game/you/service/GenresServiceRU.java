package game.you.service;

import game.you.dto.GenresDTORU;
import game.you.entity.GenresRU;
import game.you.repository.GenresRepositoryRU;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public void deleteGenre(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public GenresDTORU updateGenre(GenresDTORU genresDTORU) {
        GenresRU genresUpdate = repository.findById(genresDTORU.getId()).orElseThrow(()-> new EntityNotFoundException("no id genre"));
        if (genresDTORU.getTitle()!=null) {
            genresUpdate.setTitle(genresDTORU.getTitle());
        }
        repository.save(genresUpdate);
        GenresDTORU genresDTORUUpdate = convertToGenresDTO(genresUpdate);
        return genresDTORUUpdate;
    }
}
