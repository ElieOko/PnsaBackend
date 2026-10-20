package me.pnsa.app.structure.infrastructure.entities;

//import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("structures")
public class StructureEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("user_id")
    private Long userId;
    @Column("structure_type_id")
    private Long structureTypeId;
    @Column("province_id")
    private Long provinceId;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("is_active")
    private Boolean isActive = true;
    @Column("latitude")
    private Double latitude = 0.0;
    @Column("longitude")
    private Double longitude =  0.0;
    @Column("created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column("updated_date")
    private LocalDateTime updatedDate = LocalDateTime.now();

    public Long getId() {return id;}
    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public Long getStructureTypeId() {return structureTypeId;}
    public void setStructureTypeId(Long structureTypeId) {this.structureTypeId = structureTypeId;}
    public Long getProvinceId() {return provinceId;}
    public void setProvinceId(Long provinceId) {this.provinceId = provinceId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public Boolean getIsActive() {return isActive;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Double getLatitude() {return latitude;}
    public void setLatitude(Double latitude) {this.latitude = latitude;}
    public Double getLongitude() {return longitude;}
    public void setLongitude(Double longitude) {this.longitude = longitude;}
    public LocalDateTime getCreatedDate() {return createdDate;}
    public LocalDateTime getUpdatedDate() {return updatedDate;}
}
