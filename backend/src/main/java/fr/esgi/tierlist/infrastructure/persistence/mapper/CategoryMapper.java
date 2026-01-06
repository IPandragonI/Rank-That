package fr.esgi.tierlist.infrastructure.persistence.mapper;

import fr.esgi.tierlist.domain.model.Category;
import fr.esgi.tierlist.infrastructure.persistence.entity.CategoryEntity;

public record CategoryMapper() {

    public static Category toDomain(CategoryEntity c) {
        if (c == null) return null;
        Category category = new Category();
        category.setId(c.getId());
        category.setName(c.getName());
        category.setDescription(c.getDescription());
        category.setCreatedAt(c.getCreatedAt());
        category.setUpdatedAt(c.getUpdatedAt());
        return category;
    }

    public static CategoryEntity toEntity(Category c) {
        if (c == null) return null;
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(c.getId());
        categoryEntity.setName(c.getName());
        categoryEntity.setDescription(c.getDescription());
        categoryEntity.setCreatedAt(c.getCreatedAt());
        categoryEntity.setUpdatedAt(c.getUpdatedAt());
        return categoryEntity;
    }
}
