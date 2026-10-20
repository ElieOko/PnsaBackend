package me.pnsa.app.structure.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("responsibilities")
public class ResponsibilityEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("full_name")
    private String fullName;
    @Column("functions")
    private String function;
    @Column("structure_id")
    private Long structureId;
    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    public void setFullName(String fullName) {this.fullName = fullName;}
    public void setFunction(String function) {this.function = function;}
    public void setStructureId(Long structureId) {this.structureId = structureId;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public void setId(Long id) {this.id = id;}

    public Long getId() {return id;}
    public String getFullName() {return fullName;}
    public String getFunction() {return function;}
    public Long getStructureId() {return structureId;}
    public Boolean getIsActive() {return isActive;}

}
