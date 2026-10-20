package me.pnsa.app.address.infrastructure.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;

@Table("address")
//@Entity("address")
public class AddressEntity {
    @Id
    @Column("id")
    private Long addressId = 0L;
    @Column("avenue")
    private String avenue = "";
    @Column("avenue")
    private String quartier = "";
    @Column("fullAddress")
    private String fullAddress;
    @Column("structureId")
    private Long structureId;
    @Column("isActive")
    private Boolean isActive = true;
    public void setActive(Boolean active) {isActive = active;}
    public void setAddressId(Long addressId) {this.addressId = addressId;}
    public void setAvenue(String avenue) {this.avenue = avenue;}
    public void setFullAddress(String fullAddress) {this.fullAddress = fullAddress;}
    public void setQuartier(String quartier) {this.quartier = quartier;}
    public void setStructureId(Long structureId) {this.structureId = structureId;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Long getAddressId() {return addressId;}
    public String getAvenue() {return avenue;}
    public String getFullAddress() {return fullAddress;}
    public String getQuartier() {return quartier;}
    public Long getStructureId() {return structureId;}
    public Boolean getIsActive() {return isActive;}
}
