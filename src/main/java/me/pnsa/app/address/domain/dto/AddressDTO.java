package me.pnsa.app.address.domain.dto;

public record AddressDTO(
     String avenue,
     String quartier,
     String fullAddress,
     Long structureId
){}
