package me.pnsa.app.address.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TProvinces")
public class ProvinceEntity{
    @Id
    @Column("id")
    private Long provinceId = 0L;
    @Column("name")
    private String name;
    @Column("isActive")
    private Boolean isActive;
}
