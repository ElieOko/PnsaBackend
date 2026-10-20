package me.pnsa.app.auth.domain.models;

public record UserAuth(
        String identification,
        String password
) {
}