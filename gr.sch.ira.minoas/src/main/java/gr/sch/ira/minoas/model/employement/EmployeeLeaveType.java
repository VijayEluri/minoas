package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.BaseIDModel;
import gr.sch.ira.minoas.model.employee.EmployeeType;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_LEAVE_TYPE")

public class EmployeeLeaveType extends BaseIDModel {

    /**
     * Comment for <code>serialVersionUID</code> 
     */
    private static final long serialVersionUID = 1L;
    

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 255)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name="BASIC_TYPE", nullable=true)
    private EmployeeBasicLeaveType basicType;
    
    @Basic
    @Column(name="GENERATES_CDRS", nullable=true)
    private Boolean generatesCDRs = Boolean.FALSE;
    
    @Basic
    @Column(name="LEGACY_CODE", nullable=false, unique=true)
    private String legacyCode;
    
    @Enumerated(EnumType.STRING)
    @Column(name="EMPLOYEE_TYPE", nullable=false)
    private EmployeeType suitableForEmployeeType;
   
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the basicType
     */
    public EmployeeBasicLeaveType getBasicType() {
        return basicType;
    }

    /**
     * @param basicType the basicType to set
     */
    public void setBasicType(EmployeeBasicLeaveType basicType) {
        this.basicType = basicType;
    }

    /**
     * @return the generatesCDRs
     */
    public Boolean getGeneratesCDRs() {
        return generatesCDRs;
    }

    /**
     * @param generatesCDRs the generatesCDRs to set
     */
    public void setGeneratesCDRs(Boolean generatesCDRs) {
        this.generatesCDRs = generatesCDRs;
    }

    /**
     * @return the legacyCode
     */
    public String getLegacyCode() {
        return legacyCode;
    }

    /**
     * @param legacyCode the legacyCode to set
     */
    public void setLegacyCode(String legacyCode) {
        this.legacyCode = legacyCode;
    }

    /**
     * @return the suitableForEmployeeType
     */
    public EmployeeType getSuitableForEmployeeType() {
        return suitableForEmployeeType;
    }

    /**
     * @param suitableForEmployeeType the suitableForEmployeeType to set
     */
    public void setSuitableForEmployeeType(EmployeeType suitableForEmployeeType) {
        this.suitableForEmployeeType = suitableForEmployeeType;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EmployeeLeaveType [legacyCode=");
        builder.append(legacyCode);
        builder.append(", description=");
        builder.append(description);
        builder.append(", generatesCDRs=");
        builder.append(generatesCDRs);
        builder.append("]");
        return builder.toString();
    }
}
