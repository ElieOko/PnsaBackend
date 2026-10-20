package me.pnsa.app.users.domain.models;

public record AccountUser(
        Long accountUserId,
        Long accountId,
        Long userId,
        Boolean isActive
) {
}
