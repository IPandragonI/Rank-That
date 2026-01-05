package fr.esgi.tierlist.infrastructure.persistence.mapper;

import fr.esgi.tierlist.domain.model.TierList;
import fr.esgi.tierlist.infrastructure.persistence.entity.TierListEntity;

import java.time.LocalDateTime;

public record TierListMapper() {

    public static TierList toDomain(TierListEntity t) {
        if (t == null) return null;
        TierList tierList = new TierList();
        tierList.setId(t.getId());
        tierList.setName(t.getName());
        tierList.setCreator(UserMapper.toDomain(t.getCreator()));
        tierList.setCreatedAt(t.getCreatedAt());
        tierList.setUpdatedAt(t.getUpdatedAt());
        return tierList;
    }

    public static TierListEntity toEntity(TierList t) {
        if (t == null) return null;
        TierListEntity tierListEntity = new TierListEntity();
        tierListEntity.setId(t.getId());
        tierListEntity.setName(t.getName());
        tierListEntity.setCreator(UserMapper.toEntity(t.getCreator()));
        tierListEntity.setCreatedAt(t.getCreatedAt());
        tierListEntity.setUpdatedAt(t.getUpdatedAt());
        return tierListEntity;
    }

    public static void prepareForSave(TierListEntity t) {
        LocalDateTime now = LocalDateTime.now();
        if (t.getCreatedAt() == null) t.setCreatedAt(now);
        t.setUpdatedAt(now);
    }
}
