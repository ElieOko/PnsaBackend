package me.pnsa.app.address.infrastructure.entities;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("phone_numbers")
//@Entity("phone_numbers")
public class PhoneNumberEntity{
    @Id @Column("id") private Long PhoneNumberId = 0L;
    @Column("fullPhoneNumber") private String fullPhoneNumber;
    @Column("structureId") private Long structureId;
    @Column("isActive") private Boolean isActive = true;

    public void setFullPhoneNumber(String fullPhoneNumber) {this.fullPhoneNumber = fullPhoneNumber;}
    public void setStructureId(Long structureId) {this.structureId = structureId;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Long getPhoneNumberId() {return PhoneNumberId;}
    public String getFullPhoneNumber() {return fullPhoneNumber;}
    public Long getStructureId() {return structureId;}
    public Boolean getIsActive() {return isActive;}
    public void setPhoneNumberId(Long phoneNumberId) {this.PhoneNumberId = phoneNumberId;}
}
