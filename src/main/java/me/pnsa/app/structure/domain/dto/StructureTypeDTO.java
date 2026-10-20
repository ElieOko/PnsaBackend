package me.pnsa.app.structure.domain.dto;

public record StructureTypeDTO(
        Long structureTypeId,
        String name,
        Boolean isActive
) {
}
