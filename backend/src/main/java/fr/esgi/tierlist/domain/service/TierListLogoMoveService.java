package fr.esgi.tierlist.domain.service;

import fr.esgi.tierlist.application.form.TierListLogoMoveForm;
import fr.esgi.tierlist.domain.model.*;
import fr.esgi.tierlist.domain.port.TierListLogoMoveDatasourcePort;
import fr.esgi.tierlist.infrastructure.security.IAuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TierListLogoMoveService {
    private final TierListLogoMoveDatasourcePort tierListLogoMoveDatasourcePort;
    private final TierListService tierListService;
    private final ColumnService columnService;
    private final LogoService logoService;
    private final IAuthenticationFacade authenticationFacade;

    public TierListLogoMove createOrUpdate(TierListLogoMoveForm tierListLogoForm) {
        Optional<TierListLogoMove> existingMove = tierListLogoMoveDatasourcePort
                .findByTierListIdAndLogoId(tierListLogoForm.getTierListId(), tierListLogoForm.getLogoId());

        if (existingMove.isPresent()) {
            return update(existingMove.get(), tierListLogoForm);
        } else {
            return create(tierListLogoForm);
        }
    }
    
    private TierListLogoMove create(TierListLogoMoveForm tierListLogoForm) {
        User user = authenticationFacade.getCurrentUser();
        TierList tierList = tierListService.findById(tierListLogoForm.getTierListId());
        Column column = columnService.findById(tierListLogoForm.getColumnId());
        Logo logo = logoService.findById(tierListLogoForm.getLogoId());
        
        validateEntities(user, tierList, column, logo);

        TierListLogoMove tierListLogoMove = new TierListLogoMove();
        tierListLogoMove.setTierList(tierList);
        tierListLogoMove.setColumn(column);
        tierListLogoMove.setLogo(logo);
        tierListLogoMove.setUser(user);
        tierListLogoMove.setCreatedAt(LocalDateTime.now());

        return tierListLogoMoveDatasourcePort.save(tierListLogoMove);
    }

    private TierListLogoMove update(TierListLogoMove existingMove, TierListLogoMoveForm tierListLogoForm) {
        User user = authenticationFacade.getCurrentUser();
        Column column = columnService.findById(tierListLogoForm.getColumnId());

        if (user == null) {
            throw new IllegalArgumentException("Invalid data provided: missing user");
        }
        if (column == null) {
            throw new IllegalArgumentException("Invalid data provided: missing column");
        }

        existingMove.setColumn(column);
        existingMove.setUser(user);
        existingMove.setCreatedAt(LocalDateTime.now());

        return tierListLogoMoveDatasourcePort.save(existingMove);
    }

    private void validateEntities(User user, TierList tierList, Column column, Logo logo) {
        StringBuilder missing = new StringBuilder();
        if (user == null) missing.append("user, ");
        if (tierList == null) missing.append("tierList, ");
        if (column == null) missing.append("column, ");
        if (logo == null) missing.append("logo, ");

        if (!missing.isEmpty()) {
            String missingFields = missing.substring(0, missing.length() - 2);
            throw new IllegalArgumentException("Invalid data provided: missing " + missingFields);
        }
    }

    public List<TierListLogoMove> findAllByTierListId(Long tierListId) {
        return tierListLogoMoveDatasourcePort.findAllByTierListId(tierListId);
    }
}
