package me.pnsa.app.users.infrastructure.entities;

//import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

@Table("user_young")
//@Entity("user_young")
public class UserYoungEntity {
    @Id
    @Column("id") private Long id;
    @Column("phone_number") private String phoneNumber;
    @Column("is_lock") private Boolean isLock = false;
    @Column("is_active") private Boolean isActive = true;

    public void setActive(Boolean active) {isActive = active;}
    public void setLock(Boolean lock) {isLock = lock;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setIsLock(Boolean isLock) {this.isLock = isLock;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Long getId() {return id;}
    public String getPhoneNumber() {return phoneNumber;}
    public Boolean getIsLock() {return isLock;}
    public Boolean getIsActive() {return isActive;}

}
