package fr.esgi.tierlist.application.dto;

import fr.esgi.tierlist.domain.model.Category;

import java.io.Serializable;

public record CategoryDto(Long id, String name, String description) implements Serializable {

    public static CategoryDto transfer(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
