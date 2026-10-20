package me.pnsa.app.address.domain.models;

public record Email(
        Long emailId,
        String fullEmail,
        Long structureId,
        Boolean isActive
) {
}
