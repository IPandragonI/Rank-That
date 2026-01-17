package fr.esgi.tierlist.application.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TierListLogoMoveForm {

    @NotNull(message = "Tier List ID is required")
    private Long tierListId;

    @NotNull(message = "Logo ID is required")
    private Long logoId;

    @NotNull(message = "Column ID is required")
    private Long columnId;
}