package me.pnsa.app.address.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("address")
public class AddressEntity {
    @Id
    @Column("id")
    private Long addressId;

    @Column("avenue")
    private String avenue;

    @Column("full_address")
    private String fullAddress;

    @Column("structure_id")
    private Long structureId;

    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    public void setAddressId(Long addressId) {this.addressId = addressId;}
    public Long getAddressId() {return addressId;}
    public void setAvenue(String avenue) {this.avenue = avenue;}
    public String getAvenue() {return avenue;}
    public void setFullAddress(String fullAddress) {this.fullAddress = fullAddress;}
    public String getFullAddress() {return fullAddress;}
    public void setStructureId(Long structureId) {this.structureId = structureId;}
    public Long getStructureId() {return structureId;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Boolean getIsActive() {return isActive;}
}
