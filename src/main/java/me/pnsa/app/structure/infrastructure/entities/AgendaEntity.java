package me.pnsa.app.structure.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("agendas")
public class AgendaEntity {
    @Id
    @Column("id")
    private Long agendaId;
    @Column("day_id")
    private Long dayId;
    @Column("structure_id")
    private Long structureId;
    @Column("hour_start")
    private String hourStart;
    @Column("hour_end")
    private String hourEnd;
    @Column("is_active")
    private Boolean isActive = Boolean.TRUE;

    public void setAgendaId(Long agendaId) {this.agendaId = agendaId;}
    public void setDayId(Long dayId) {this.dayId = dayId;}
    public void setStructureId(Long structureId) {this.structureId = structureId;}
    public void setHourStart(String hourStart) {this.hourStart = hourStart;}
    public void setHourEnd(String hourEnd) {this.hourEnd = hourEnd;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public Long getAgendaId() {return agendaId;}
    public Long getDayId() {return dayId;}
    public Long getStructureId() {return structureId;}
    public String getHourStart() {return hourStart;}
    public String getHourEnd() {return hourEnd;}
    public Boolean getIsActive() {return isActive;}
    
}
