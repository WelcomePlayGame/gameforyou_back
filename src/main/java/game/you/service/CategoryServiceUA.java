package game.you.service;

import game.you.dto.CategoryDTOUA;
import game.you.entity.CategoryUA;
import game.you.repository.CategoryRepositoryUA;
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
public class CategoryServiceUA {

    final private CategoryRepositoryUA repository;
    final private ModelMapper modelMapper;
    @Transactional
    public CategoryDTOUA addCategory(CategoryDTOUA categoryDTOUA) {
        CategoryUA categoryUA = convertToCategoryUA(categoryDTOUA);
        repository.save(categoryUA);
        return categoryDTOUA;
    }

    @Cacheable(value = "category", key = "1")
    public List<CategoryDTOUA> getListCategoryDTo() {
        return repository.findAll().stream().map(this::convertToCategoryDTOUA).collect(Collectors.toList());
    }

    CategoryUA convertToCategoryUA (CategoryDTOUA categoryDTOUA) {
        return modelMapper.map(categoryDTOUA, CategoryUA.class);
    }
    CategoryDTOUA convertToCategoryDTOUA (CategoryUA categoryUA) {
        return modelMapper.map(categoryUA, CategoryDTOUA.class);
    }
}
