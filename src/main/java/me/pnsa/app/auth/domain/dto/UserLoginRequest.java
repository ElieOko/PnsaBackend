package me.pnsa.app.auth.domain.dto;

public record UserLoginRequest(
        String phoneNumber,
        String pin
) {
}
