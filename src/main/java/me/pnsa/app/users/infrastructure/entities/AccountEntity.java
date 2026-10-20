package me.pnsa.app.users.infrastructure.entities;

//import jakarta.persistence.*;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

//@Entity("accounts")
@Table("accounts")
public class AccountEntity {
    @Id
    @Column("id") private Long accountId;
    @Column("name") private Long name;
    @Column("is_active") private Boolean isActive;

    public void setAccountId(Long accountId) {this.accountId = accountId;}
    public void setName(Long name) {this.name = name;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Long getAccountId() {return accountId;}
    public Long getName() {return name;}
    public Boolean getIsActive() {return isActive;}

}
