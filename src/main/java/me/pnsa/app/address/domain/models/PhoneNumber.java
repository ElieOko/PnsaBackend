package me.pnsa.app.address.domain.models;

public record PhoneNumber(
        Long PhoneNumberId,
        String fullPhoneNumberId,
        Long structureId,
        Boolean isActive
) {
}
