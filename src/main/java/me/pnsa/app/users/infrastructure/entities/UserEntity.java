package me.pnsa.app.users.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class UserEntity {
    @Id
    @Column("id")
    private Long userId;

    @Column("phone_number")
    private String phoneNumber;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("password_text")
    private String passwordVisible;

    @Column("is_lock")
    private Boolean isLock = Boolean.FALSE;

    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    @Column("must_change_password")
    private Boolean mustChangePassword = Boolean.TRUE;

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getPasswordVisible() {return passwordVisible;}
    public void setPasswordVisible(String passwordVisible) {this.passwordVisible = passwordVisible;}
    public Boolean getIsLock() {return isLock;}
    public void setIsLock(Boolean isLock) {this.isLock = isLock;}
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Boolean getMustChangePassword() {return mustChangePassword;}
    public void setMustChangePassword(Boolean mustChangePassword) {this.mustChangePassword = mustChangePassword;}

}
