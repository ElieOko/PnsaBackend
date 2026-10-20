package me.pnsa.app.structure.domain.dto;

public record AgendaDTO(
        Long dayId,
        Long structureId,
        String hourStart,
        String hourEnd
) {
}
