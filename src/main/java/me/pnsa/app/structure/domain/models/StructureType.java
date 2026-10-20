package me.pnsa.app.structure.domain.models;

public record StructureType(
        Long structureTypeId,
        String name,
        Boolean isActive
) {
}
