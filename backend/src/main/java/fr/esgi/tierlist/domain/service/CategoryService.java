package fr.esgi.tierlist.domain.service;

import fr.esgi.tierlist.application.form.CategoryForm;
import fr.esgi.tierlist.domain.model.Category;
import fr.esgi.tierlist.domain.port.CategoryDatasourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDatasourcePort categoryDatasourcePort;

    public Category create(CategoryForm categoryForm) {
        Category category = new Category();
        category.setName(categoryForm.getName());
        category.setDescription(categoryForm.getDescription());
        return categoryDatasourcePort.save(category);
    }

    public Category findById(Long id) {
        return categoryDatasourcePort.findById(id).orElse(null);
    }

    public List<Category> findAll() {
        return categoryDatasourcePort.findAll();
    }

    public Category update(Long id, CategoryForm categoryForm) {
        Category category = findById(id);
        if (category == null) {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }
        category.setName(categoryForm.getName());
        category.setDescription(categoryForm.getDescription());
        return categoryDatasourcePort.save(category);
    }

    public void delete(Long id) {
        categoryDatasourcePort.deleteById(id);
    }
}
