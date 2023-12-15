package game.you.service;

import game.you.dto.CategoryDTOPL;
import game.you.entity.CategoryPL;
import game.you.repository.CategoryRepositoryPL;
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
public class CategoryServicePL {

    final private CategoryRepositoryPL repository;
    final  private ModelMapper modelMapper;
    @Transactional
    public CategoryDTOPL addCategory(CategoryDTOPL category) {
        CategoryPL categoryPL = convertToCategoryPL(category);
        repository.save(categoryPL);
        return category;
    }

    @Cacheable(value = "category", key = "1")
    public List<CategoryDTOPL> getListCategoryDTo() {
        return repository.findAll().stream().map(this::convertToCategoryDTOPL).collect(Collectors.toList());
    }

    CategoryPL convertToCategoryPL (CategoryDTOPL categoryDTOEN) {
        return modelMapper.map(categoryDTOEN, CategoryPL.class);
    }
    CategoryDTOPL convertToCategoryDTOPL (CategoryPL categoryPL) {
        return  modelMapper.map(categoryPL, CategoryDTOPL.class);
    }
}
