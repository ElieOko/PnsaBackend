package me.pnsa.app.users.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

@Table("accounts")
public class AccountEntity {
    @Id
    @Column("id")
    private Long accountId;

    @Column("name")
    private String name;

    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    public void setAccountId(Long accountId) {this.accountId = accountId;}
    public Long getAccountId() {return accountId;}
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Boolean getIsActive() {return isActive;}

}
