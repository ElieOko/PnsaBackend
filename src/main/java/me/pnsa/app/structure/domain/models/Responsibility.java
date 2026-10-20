package me.pnsa.app.structure.domain.models;

public record Responsibility(
        Long responsibilityId,
        String fullName,
        String function,
        Long structureId,
        Boolean isActive
) {
}
