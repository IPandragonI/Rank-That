package fr.esgi.tierlist.application.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {

    @NotBlank(message = "Name is required")
    private String name;
    private String description;
}
