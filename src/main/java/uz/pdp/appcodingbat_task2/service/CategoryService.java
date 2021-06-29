package uz.pdp.appcodingbat_task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat_task2.entity.Category;
import uz.pdp.appcodingbat_task2.entity.ProgramLanguage;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.repository.CategoryRepository;
import uz.pdp.appcodingbat_task2.repository.ProgramLanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProgramLanguageRepository programLanguageRepository;


    public List<Category> getCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;

    }

    public Category getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }


    public ApiResponse deleteById(Integer id) {
        try {
            categoryRepository.getById(id);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Category Not Found", false);
        }
    }

    public ApiResponse add(Category category) {
        Optional<ProgramLanguage> optionalProgramLanguage = programLanguageRepository.findById(category.getProgramLanguage().getId());
        if (!optionalProgramLanguage.isPresent()) {
            return new ApiResponse("Program Language not found", false);
        }
        ProgramLanguage programLanguage = optionalProgramLanguage.get();
        Category category1 = new Category();
        category1.setName(category1.getName());
        category1.setProgramLanguage(programLanguage);
        categoryRepository.save(category1);
        return new ApiResponse("Successfully added", true);
    }


    public ApiResponse edit(Integer id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found", false);
        }
        Category editingCategory = optionalCategory.get();

        Optional<ProgramLanguage> optionalProgramLanguage = programLanguageRepository.findById(category.getProgramLanguage().getId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Program language not found", false);
        }
        ProgramLanguage programLanguage = optionalProgramLanguage.get();
        editingCategory.setName(category.getName());
        editingCategory.setProgramLanguage(programLanguage);
        categoryRepository.save(editingCategory);
        return new ApiResponse("Successfully edited", true);
    }
}
