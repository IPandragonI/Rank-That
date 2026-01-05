package fr.esgi.tierlist.domain.port;

import fr.esgi.tierlist.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDatasourcePort {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
}
