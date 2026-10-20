package me.pnsa.app.users.domain.models;

public record Account(
        Long accountId,
        Long name,
        Boolean isActive
) {
}
