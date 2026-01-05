package fr.esgi.tierlist.application.controller;

import fr.esgi.tierlist.domain.model.TierList;
import fr.esgi.tierlist.application.dto.TierListDto;
import fr.esgi.tierlist.application.form.TierListForm;
import fr.esgi.tierlist.domain.service.TierListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tier-lists")
public class TierListController {

    private final TierListService tierListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TierListDto createTierList(@RequestBody TierListForm tierListForm) {
        TierList tierList = tierListService.create(tierListForm);
        return TierListDto.transfer(tierList);
    }

    @GetMapping("/{id}")
    public TierListDto findTierListById(@PathVariable Long id) {
        TierList tierList = tierListService.findById(id);
        return TierListDto.transfer(tierList);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TierListDto updateTierList(@PathVariable Long id, @RequestBody TierListForm tierListForm) {
        TierList tierList = tierListService.update(id, tierListForm);
        return TierListDto.transfer(tierList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTierList(@PathVariable Long id) {
        tierListService.delete(id);
    }

}
