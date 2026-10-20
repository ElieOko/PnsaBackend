package me.pnsa.app.structure.infrastructure.entities;

//import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("structure_types")
//@Entity(name = "structure_types")
public class StructureTypeEntity {
    @Id
    @Column("id")
    private Long id = 0L;
    @Column("name")
    private String name;
    @Column("is_active")
    private Boolean isActive;

    public Boolean getActive() {return isActive;}
    public void setActive(Boolean active) {isActive = active;}
    public Long getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
