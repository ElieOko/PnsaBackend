package me.pnsa.app.users.domain.models;

public record Account(
        Long accountId,
        String name,
        Boolean isActive
) {
}
