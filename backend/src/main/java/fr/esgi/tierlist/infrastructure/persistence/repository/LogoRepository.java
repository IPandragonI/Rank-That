package fr.esgi.tierlist.infrastructure.persistence.repository;

import fr.esgi.tierlist.infrastructure.persistence.entity.LogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LogoRepository extends JpaRepository<LogoEntity, Long> {
    List<LogoEntity> findByName(String name);
    Optional<LogoEntity> findByDomain(String domain);
    List<LogoEntity> findByDomainContainingIgnoreCase(String domainPattern);
    boolean existsByDomain(String domain);
}