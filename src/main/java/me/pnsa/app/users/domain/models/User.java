package me.pnsa.app.users.domain.models;

public record User(
        Long userId,
        String phoneNumber,
        String email,
        String password,
        Boolean isLock,
        Boolean isActive
) {
}
