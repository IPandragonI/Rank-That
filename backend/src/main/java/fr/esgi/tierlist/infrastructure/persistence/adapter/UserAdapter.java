package fr.esgi.tierlist.infrastructure.persistence.adapter;

import fr.esgi.tierlist.domain.model.User;
import fr.esgi.tierlist.infrastructure.persistence.entity.UserEntity;
import fr.esgi.tierlist.infrastructure.persistence.mapper.UserMapper;
import fr.esgi.tierlist.domain.port.UserDatasourcePort;
import fr.esgi.tierlist.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserAdapter implements UserDatasourcePort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserMapper.prepareForSave(userEntity);
        UserEntity savedEntity = userRepository.save(userEntity);
        return UserMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
