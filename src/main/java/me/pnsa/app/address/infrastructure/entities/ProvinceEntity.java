package me.pnsa.app.address.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("provinces")
public class ProvinceEntity{
    @Id
    @Column("id") private Long provinceId;
    @Column("name")
    private String name;
    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    public Long getProvinceId() {return provinceId;}
    public void setProvinceId(Long provinceId) {this.provinceId = provinceId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
}
