package game.you.service;

import game.you.dto.CategoryDTOEN;
import game.you.dto.CategoryDTOPL;
import game.you.entity.CategoryEN;
import game.you.entity.CategoryPL;
import game.you.repository.CategoryRepositoryEN;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceEN {

    final private CategoryRepositoryEN repository;
    final  private ModelMapper modelMapper;
    @Transactional
    public CategoryDTOEN addCategory(CategoryDTOEN category) {
        CategoryEN categoryEN = convertToCategoryEN(category);
        repository.save(categoryEN);
        return  category;
    }

    @Cacheable(value = "category", key = "1")
    public List<CategoryDTOEN> getListCategoryDTo() {
        return repository.findAll().stream().map(this::convertToCategoryDTOEN).collect(Collectors.toList());
    }

    CategoryEN convertToCategoryEN (CategoryDTOEN categoryDTOEN) {
        return modelMapper.map(categoryDTOEN, CategoryEN.class);
    }
    CategoryDTOEN convertToCategoryDTOEN (CategoryEN categoryEN) {
        return  modelMapper.map(categoryEN, CategoryDTOEN.class);
    }
}
