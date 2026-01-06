package fr.esgi.tierlist.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TierList {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private User creator;

    private List<Column> columns;

    private List<Logo> logos;

    private Category category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
