package fr.esgi.tierlist.domain.port;

import fr.esgi.tierlist.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDatasourcePort {
    Optional<Category> findById(Long id);
    List<Category> findAll();
    Category save(Category column);
    void deleteById(Long id);
}
