package fr.esgi.tierlist.domain.port;

import fr.esgi.tierlist.domain.model.TierListLogoMove;

import java.util.List;
import java.util.Optional;

public interface TierListLogoMoveDatasourcePort {
    Optional<TierListLogoMove> findById(Long id);
    TierListLogoMove save(TierListLogoMove tierListLogoMove);
    List<TierListLogoMove> findAllByTierListId(Long tierListId);
    Optional<TierListLogoMove> findByTierListIdAndLogoId(Long tierListId, Long logoId);
    void deleteById(Long id);
}
