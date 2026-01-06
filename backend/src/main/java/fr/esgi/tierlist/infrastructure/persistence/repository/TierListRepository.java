package fr.esgi.tierlist.infrastructure.persistence.repository;

import fr.esgi.tierlist.infrastructure.persistence.entity.TierListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TierListRepository extends JpaRepository<TierListEntity, Long> {
    Optional<TierListEntity> findByCreatorId(Long creatorId);
}