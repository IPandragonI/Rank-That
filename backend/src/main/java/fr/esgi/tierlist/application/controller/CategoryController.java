package fr.esgi.tierlist.application.controller;

import fr.esgi.tierlist.application.dto.CategoryDto;
import fr.esgi.tierlist.application.form.CategoryForm;
import fr.esgi.tierlist.domain.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all categories")
    public List<CategoryDto> findAll() {
        return categoryService.findAll().stream().map(CategoryDto::transfer).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find category by ID")
    public CategoryDto findById(@PathVariable Long id) {
        return CategoryDto.transfer(categoryService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new category")
    public CategoryDto create(@RequestBody CategoryForm categoryForm) {
        return CategoryDto.transfer(categoryService.create(categoryForm));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update category by ID")
    public CategoryDto update(@PathVariable Long id, @RequestBody CategoryForm categoryForm) {
        return CategoryDto.transfer(categoryService.update(id, categoryForm));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete category by ID")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
