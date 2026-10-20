package me.pnsa.app.auth.domain.dto;

public record ChangePinRequest(
        String phoneNumber,
        String oldPin,
        String newPin
) {
}
