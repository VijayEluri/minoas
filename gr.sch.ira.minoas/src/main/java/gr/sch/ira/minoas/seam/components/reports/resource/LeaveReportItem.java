package gr.sch.ira.minoas.seam.components.reports.resource;

import gr.sch.ira.minoas.model.employement.EmployeeLeave;

import java.util.Date;

public class LeaveReportItem extends EmployeeReportItem {
	
	private String comment;

	private Date dueTo;

	private Date establishedIn;

	private String leaveType;

	private String leaveTypeKey;

	private String regularUnit;
	
	private Integer leaveEffectiveDuration;
	
	public LeaveReportItem() {
		super();
	}

	public LeaveReportItem(EmployeeLeave leave) {
		super(leave.getEmployee());
		this.comment = leave.getComment();
		this.dueTo = leave.getDueTo();
		this.establishedIn = leave.getEstablished();
		if (leave.getEmployee().getCurrentEmployment() != null)
			this.regularUnit = leave.getEmployee().getCurrentEmployment().getSchool().getTitle();
		this.leaveType = leave.getEmployeeLeaveType().getDescription();
		this.leaveTypeKey = leave.getEmployeeLeaveType().getLegacyCode();
		this.leaveEffectiveDuration = leave.getEffectiveNumberOfDays();
		setInsertedOn(leave.getInsertedOn());
	}

	public String getComment() {
		return comment;
	}

	public Date getDueTo() {
		return dueTo;
	}

	public Date getEstablishedIn() {
		return establishedIn;
	}

	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @return the leaveTypeKey
	 */
	public String getLeaveTypeKey() {
		return leaveTypeKey;
	}

	/**
	 * @return the regularUnit
	 */
	public String getRegularUnit() {
		return regularUnit;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param dueTo the dueTo to set
	 */
	public void setDueTo(Date dueTo) {
		this.dueTo = dueTo;
	}

	/**
	 * @param establishedIn the establishedIn to set
	 */
	public void setEstablishedIn(Date establishedIn) {
		this.establishedIn = establishedIn;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * @param leaveTypeKey the leaveTypeKey to set
	 */
	public void setLeaveTypeKey(String leaveTypeKey) {
		this.leaveTypeKey = leaveTypeKey;
	}

	/**
	 * @param regularUnit the regularUnit to set
	 */
	public void setRegularUnit(String regularUnit) {
		this.regularUnit = regularUnit;
	}

    /**
     * @return the leaveEffectiveDuration
     */
    public Integer getLeaveEffectiveDuration() {
        return leaveEffectiveDuration;
    }

    /**
     * @param leaveEffectiveDuration the leaveEffectiveDuration to set
     */
    public void setLeaveEffectiveDuration(Integer leaveEffectiveDuration) {
        this.leaveEffectiveDuration = leaveEffectiveDuration;
    }

}