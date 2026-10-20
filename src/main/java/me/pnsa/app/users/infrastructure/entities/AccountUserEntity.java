package me.pnsa.app.users.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

@Table("account_users")
public class AccountUserEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("account_id")
    private Long accountId;
    @Column("user_id")
    private Long userId;
    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public void setAccountId(Long accountId) {this.accountId = accountId;}
    public Long getAccountId() {return accountId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public Long getUserId() {return userId;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Boolean getIsActive() {return isActive;}

}
