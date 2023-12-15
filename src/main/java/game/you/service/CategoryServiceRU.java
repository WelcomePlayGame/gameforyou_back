package game.you.service;


import game.you.dto.CategoryDTORU;
import game.you.entity.CategoryRU;
import game.you.repository.CategoryRepositoryRU;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceRU {

    final private CategoryRepositoryRU repository;
    final  private ModelMapper modelMapper;
    @Transactional
    public CategoryDTORU addCategory(CategoryDTORU category) {
        CategoryRU categoryRU = convertToCategoryRU(category);
        repository.save(categoryRU);
        return category;
    }

    @Cacheable(value = "category", key = "1")
    public List<CategoryDTORU> getListCategoryDTo() {
        return repository.findAll().stream().map(this::convertToCategoryDTORU).collect(Collectors.toList());
    }

    CategoryRU convertToCategoryRU (CategoryDTORU categoryDTORU) {
        return modelMapper.map(categoryDTORU, CategoryRU.class);
    }
    CategoryDTORU convertToCategoryDTORU (CategoryRU categoryRU) {
        return  modelMapper.map(categoryRU, CategoryDTORU.class);
    }
}
