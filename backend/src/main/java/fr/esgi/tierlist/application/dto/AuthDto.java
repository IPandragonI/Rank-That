package fr.esgi.tierlist.application.dto;

import java.io.Serializable;

public record AuthDto(String token, UserDto currentUser) implements Serializable {
}
