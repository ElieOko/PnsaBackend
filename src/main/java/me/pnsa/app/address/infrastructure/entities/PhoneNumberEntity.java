package me.pnsa.app.address.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("phone_numbers")
public class PhoneNumberEntity{
    @Id
    @Column("id")
    private Long phoneNumberId;

    @Column("full_phone_number")
    private String fullPhoneNumber;

    @Column("structure_id")
    private Long structureId;

    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    public Long getPhoneNumberId() {return phoneNumberId;}
    public void setPhoneNumberId(Long phoneNumberId) {this.phoneNumberId = phoneNumberId;}
    public String getFullPhoneNumber() {return fullPhoneNumber;}
    public void setFullPhoneNumber(String fullPhoneNumber) {this.fullPhoneNumber = fullPhoneNumber;}
    public Long getStructureId() {return structureId;}
    public void setStructureId(Long structureId) {this.structureId = structureId;}
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
}
