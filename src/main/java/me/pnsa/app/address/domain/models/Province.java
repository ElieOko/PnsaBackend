package me.pnsa.app.address.domain.models;

public record Province(
        Long provinceId,
        String name,
        Boolean isActive
) {
}
