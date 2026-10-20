package me.pnsa.app.users.infrastructure.entities;

//import jakarta.persistence.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//@Entity("users")
@Table("users")
public class UserEntity {
    @Id
    @Column("id") private Long userId;
    @Column("phoneNumber") private String phoneNumber;
    @Column("email") private String email;
    @Column("password_text") protected String passwordVisible;
    @Column("is_lock") private Boolean isLock;
    @Column("is_active") private Boolean isActive;

    public void setEmail(String email) {this.email = email;}
    public void setPasswordVisible(String passwordVisible) {this.passwordVisible = passwordVisible;}
    public void setIsLock(Boolean isLock) {this.isLock = isLock;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setPassword(String password) {this.passwordVisible = password;}
    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getEmail() {return email;}
    public String getPasswordVisible() {return passwordVisible;}
    public Boolean getIsLock() {return isLock;}
    public Boolean getIsActive() {return isActive;}

}
