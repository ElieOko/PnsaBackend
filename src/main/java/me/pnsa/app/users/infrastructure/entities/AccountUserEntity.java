package me.pnsa.app.users.infrastructure.entities;

//import jakarta.persistence.*;
//import jakarta.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

@Table("account_users")
//@Entity("account_users")
public class AccountUserEntity {
    @Id
    private Long id;
    @Column("account_id") private Long accountId;
    @Column("user_id") private Long userId;
    @Column("is_active") private Boolean isActive;

    public void setAccountId(Long accountId) {this.accountId = accountId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Boolean getActive() {return isActive;}
    public Long getId() {return id;}

    public Long getAccountId() {return accountId;}
    public Long getUserId() {return userId;}

}
