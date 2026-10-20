package me.pnsa.app.users.domain.dto;

public record CreateUserRequest(
        String phoneNumber,
        String email,
        String pin
) {
}
