package dev.patika.api;

import dev.patika.business.abstracts.ICategoryService;
import dev.patika.core.config.modelMapper.IModelMapperService;
import dev.patika.dto.request.category.CategorySaveRequest;
import dev.patika.dto.request.category.CategoryUpdateRequest;
import dev.patika.dto.response.category.CategoryResponse;
import dev.patika.entity.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryResponse save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category category = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        categoryService.save(category);
        return this.modelMapper.forResponse().map(category, CategoryResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CategoryResponse get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);
        return this.modelMapper.forResponse().map(category, CategoryResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CategoryResponse update(@PathVariable("id") int id, @Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category category = this.categoryService.get(id);
        this.modelMapper.forRequest().map(categoryUpdateRequest, category);
        categoryService.update(category);
        return this.modelMapper.forResponse().map(category, CategoryResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        boolean isDeleted = categoryService.delete(id);
        if (isDeleted) {
            return String.format("Category with ID %d deleted", id);
        } else {
            return String.format("No Category with ID %d found", id);
        }
    }
}