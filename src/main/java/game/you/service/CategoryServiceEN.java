package game.you.service;

import game.you.dto.CategoryDTOEN;
import game.you.entity.CategoryEN;
import game.you.repository.CategoryRepositoryEN;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Cacheable(value = "category_en", key = "category_en_all")
    public List<CategoryDTOEN> getListCategoryDTo() {
        return repository.findAll().stream().map(this::convertToCategoryDTOEN).collect(Collectors.toList());
    }

    CategoryEN convertToCategoryEN (CategoryDTOEN categoryDTOEN) {
        return modelMapper.map(categoryDTOEN, CategoryEN.class);
    }
    CategoryDTOEN convertToCategoryDTOEN (CategoryEN categoryEN) {
        return  modelMapper.map(categoryEN, CategoryDTOEN.class);
    }

    @Transactional
    public void deleteCategory(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public CategoryDTOEN updateCategory(CategoryDTOEN categoryDTOEN) {
        Optional<CategoryEN> categoryUpdate = repository.findById(categoryDTOEN.getId());
        if (categoryDTOEN.getTitle()!=null) {
            categoryUpdate.get().setTitle(categoryDTOEN.getTitle());
        }
        repository.save(categoryUpdate.get());
        CategoryDTOEN categoryDTOENupdate = convertToCategoryDTOEN(categoryUpdate.get());
        return categoryDTOENupdate;
    }
}
