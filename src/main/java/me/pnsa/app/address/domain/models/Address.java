package me.pnsa.app.address.domain.models;


public record Address(
     Long addressId,
     String avenue,
     String quartier,
     String fullAddress,
     Long structureId,
     Boolean isActive
){}
