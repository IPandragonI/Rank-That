package fr.esgi.tierlist.application.controller;

import fr.esgi.tierlist.application.dto.TierListLogoMoveDto;
import fr.esgi.tierlist.application.form.TierListLogoMoveForm;
import fr.esgi.tierlist.domain.model.TierListLogoMove;
import fr.esgi.tierlist.domain.service.TierListLogoMoveService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tier-list-logo-moves")
public class TierListLogoMoveController {
    
    private final TierListLogoMoveService tierListLogoMoveService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Move a Logo to a Tier List")
    public TierListLogoMoveDto createOrUpdate(@RequestBody TierListLogoMoveForm tierListLogoMoveForm) {
        TierListLogoMove move = tierListLogoMoveService.createOrUpdate(tierListLogoMoveForm);
        return TierListLogoMoveDto.transfer(move);
    }

    @GetMapping("/tierlist/{tierlistId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Logo Moves by Tier List ID")
    public List<TierListLogoMoveDto> findAllByTierListId(@PathVariable Long tierlistId) {
        List<TierListLogoMove> moves = tierListLogoMoveService.findAllByTierListId(tierlistId);
        return moves.stream()
                .map(TierListLogoMoveDto::transfer)
                .toList();
    }

    @PostMapping("/export/{tierlistId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Export Tier List by ID")
    public ModelAndView export(@PathVariable Long tierlistId) {
        List<TierListLogoMove> tierListLogoMoves = tierListLogoMoveService.findAllByTierListId(tierlistId);
        if (tierListLogoMoves == null) {
            throw new IllegalArgumentException("No logo moves found for Tier List with id: " + tierlistId);
        }

        ModelAndView mav = new ModelAndView("exportSyntheseTierListsPdfView");
        mav.addObject("tierListLogoMoves", tierListLogoMoves);
        return mav;
    }
}
