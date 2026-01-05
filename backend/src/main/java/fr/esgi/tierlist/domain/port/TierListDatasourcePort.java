package fr.esgi.tierlist.domain.port;

import fr.esgi.tierlist.domain.model.TierList;

import java.util.Optional;

public interface TierListDatasourcePort {
    Optional<TierList> findById(Long id);
    TierList save(TierList tierList);
    void deleteById(Long id);
}
