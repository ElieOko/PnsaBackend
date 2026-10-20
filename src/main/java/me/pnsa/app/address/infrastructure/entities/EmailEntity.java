package me.pnsa.app.address.infrastructure.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("emails")
//@Entity("emails")
public class EmailEntity{
        @Id
        @Column( "id")
        private Long emailId = 0L;
        @Column("fullEmail")
        private String fullEmail;
        @Column("structureId")
        private Long structureId;
        @Column("isActive")
        private Boolean isActive;

        public Long getEmailId() {return emailId;}
        public void setEmailId(Long emailId) {this.emailId = emailId;}
        public String getFullEmail() {return fullEmail;}
        public void setFullEmail(String fullEmail) {this.fullEmail = fullEmail;}
        public Long getStructureId() {return structureId;}
        public void setStructureId(Long structureId) {this.structureId = structureId;}
        public Boolean getIsActive() {return isActive;}
        public void setIsActive(Boolean isActive) {this.isActive = isActive;}

}
