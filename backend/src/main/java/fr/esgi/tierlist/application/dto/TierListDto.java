package fr.esgi.tierlist.application.dto;

import fr.esgi.tierlist.domain.model.TierList;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link TierList}
 */
public record TierListDto(Long id, String name, UserDto creator, LocalDateTime createdAt,
                          LocalDateTime updatedAt) implements Serializable {

    public static TierListDto transfer(TierList tierList) {
        return new TierListDto(
                tierList.getId(),
                tierList.getName(),
                UserDto.transfer(tierList.getCreator()),
                tierList.getCreatedAt(),
                tierList.getUpdatedAt()
        );
    }
}