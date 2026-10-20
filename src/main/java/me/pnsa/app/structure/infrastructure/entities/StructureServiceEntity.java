package me.pnsa.app.structure.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("structure_services")
public class StructureServiceEntity {
    @Id
    @Column("id")
    private Long structureServiceId;

    @Column("structure_id")
    private Long structureId;

    @Column("service_id")
    private Long serviceId;

    public void setServiceId(Long serviceId) {this.serviceId = serviceId;}
    public void setStructureServiceId(Long structureServiceId) {this.structureServiceId = structureServiceId;}
    public Long getStructureServiceId() {return structureServiceId;}
    public Long getServiceId() {return serviceId;}
    public Long getStructureId() {return structureId;}
    public void setStructureId(Long structureId) {this.structureId = structureId;}
}
