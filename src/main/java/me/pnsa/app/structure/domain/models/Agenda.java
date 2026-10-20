package me.pnsa.app.structure.domain.models;

public record Agenda(
        Long agendaId,
        Long dayId,
        Long structureId,
        String hourStart,
        String hourEnd,
        Boolean isActive
) {
}
