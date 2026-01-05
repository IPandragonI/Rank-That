package fr.esgi.tierlist.domain.service;

import fr.esgi.tierlist.domain.model.TierList;
import fr.esgi.tierlist.domain.model.User;
import fr.esgi.tierlist.application.form.TierListForm;
import fr.esgi.tierlist.domain.port.TierListDatasourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TierListService {

    private final TierListDatasourcePort tierListDatasourcePort;
    private final UserService userService;

    public TierList create(TierListForm tierListform) {
        TierList tierList = new TierList();
        tierList.setName(tierListform.getName());
        User creator = userService.findById(tierListform.getCreatorId());
        tierList.setCreator(creator);
        return tierListDatasourcePort.save(tierList);
    }

    public TierList findById(Long id) {
        return tierListDatasourcePort.findById(id).orElse(null);
    }

    public TierList update(Long id, TierListForm tierListForm) {
        Optional<TierList> optionalTierList = tierListDatasourcePort.findById(id);
        if (optionalTierList.isPresent()) {
            TierList tierList = optionalTierList.get();
            tierList.setName(tierListForm.getName());
            return tierListDatasourcePort.save(tierList);
        }
        return null;
    }

    public void delete(Long id) {
        tierListDatasourcePort.deleteById(id);
    }
}
