package me.pnsa.app.structure.domain.dto;

public record AgendaReadDTO(
        DayReadDTO day,
        String hourStart,
        String hourEnd
) {
}
