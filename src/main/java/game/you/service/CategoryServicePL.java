package game.you.service;

import game.you.dto.CategoryDTOPL;
import game.you.entity.CategoryPL;
import game.you.repository.CategoryRepositoryPL;
import jakarta.persistence.EntityNotFoundException;
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


    public List<CategoryDTOPL> getListCategoryDTo() {
        return repository.findAll().stream().map(this::convertToCategoryDTOPL).collect(Collectors.toList());
    }

    CategoryPL convertToCategoryPL (CategoryDTOPL categoryDTOEN) {
        return modelMapper.map(categoryDTOEN, CategoryPL.class);
    }
    CategoryDTOPL convertToCategoryDTOPL (CategoryPL categoryPL) {
        return  modelMapper.map(categoryPL, CategoryDTOPL.class);
    }

    @Transactional
    public void deleteCategory(long id) {
        repository.deleteById(id);
    }
    @Transactional
    public CategoryDTOPL updateCategory(CategoryDTOPL categoryDTOPL) {
        CategoryPL categoryUpdate = repository.findById(categoryDTOPL.getId()).orElseThrow(()-> new EntityNotFoundException("no id category"));
        if (categoryDTOPL.getTitle()!=null) {
            categoryUpdate.setTitle(categoryDTOPL.getTitle());
        }
        repository.save(categoryUpdate);
        CategoryDTOPL categoryDTOPLUpdate = convertToCategoryDTOPL(categoryUpdate);
        return categoryDTOPLUpdate;
    }
}
