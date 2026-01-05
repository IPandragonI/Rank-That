package fr.esgi.tierlist.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String lastname;

    private String firstname;

    private String username;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
