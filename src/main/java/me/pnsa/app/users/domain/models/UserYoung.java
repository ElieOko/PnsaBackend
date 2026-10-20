package me.pnsa.app.users.domain.models;

public record UserYoung(
        Long userId,
        String phoneNumber,
        Boolean isLock,
        Boolean isActive
){}
