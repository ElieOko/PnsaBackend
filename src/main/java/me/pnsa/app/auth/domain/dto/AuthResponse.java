package me.pnsa.app.auth.domain.dto;

public record AuthResponse(
        Long userId,
        String phoneNumber,
        Boolean mustChangePassword,
        String message
) {
}
