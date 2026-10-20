package me.pnsa.app.structure.infrastructure.entities;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
//import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("services")
//@Entity(name = "services")
public class ServiceEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("isActive")
    private Boolean isActive;

    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
}
