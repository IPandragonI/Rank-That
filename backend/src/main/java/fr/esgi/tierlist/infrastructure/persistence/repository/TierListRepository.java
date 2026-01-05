package fr.esgi.tierlist.infrastructure.persistence.repository;

import fr.esgi.tierlist.infrastructure.persistence.entity.TierListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierListRepository extends JpaRepository<TierListEntity, Long> {
}