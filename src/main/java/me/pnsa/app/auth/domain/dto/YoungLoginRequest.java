package me.pnsa.app.auth.domain.dto;

public record YoungLoginRequest(
        String phoneNumber,
        String pin
) {
}
