package me.pnsa.app.users.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

@Table("user_young")
public class UserYoungEntity {
    @Id
    @Column("id")
    private Long id;

    @Column("phone_number")
    private String phoneNumber;

    @Column("password")
    private String password;

    @Column("is_lock")
    private Boolean isLock = Boolean.FALSE;

    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    @Column("must_change_password")
    private Boolean mustChangePassword = Boolean.TRUE;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public Boolean getIsLock() {return isLock;}
    public void setIsLock(Boolean isLock) {this.isLock = isLock;}
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Boolean getMustChangePassword() {return mustChangePassword;}
    public void setMustChangePassword(Boolean mustChangePassword) {this.mustChangePassword = mustChangePassword;}

}
