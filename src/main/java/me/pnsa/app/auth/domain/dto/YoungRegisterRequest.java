package me.pnsa.app.auth.domain.dto;

public record YoungRegisterRequest(
        String phoneNumber,
        String pin
) {
}
