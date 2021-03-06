package gr.sch.ira.minoas.seam.components;

import gr.sch.ira.minoas.core.CoreUtils;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.EmployeeInfo;
import gr.sch.ira.minoas.model.employee.EmployeeType;
import gr.sch.ira.minoas.model.employee.Penalty;
import gr.sch.ira.minoas.model.employee.RegularEmployeeInfo;
import gr.sch.ira.minoas.model.employement.EmployeeLeave;
import gr.sch.ira.minoas.model.employement.Employment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Name("workExperienceCalculation")
@Scope(ScopeType.APPLICATION)
@AutoCreate
public class WorkExperienceCalculation extends BaseDatabaseAwareSeamComponent {

	/**
	 * Helper class for holding / storing employee's service values
	 * 
	 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
	 * @version $Id$
	 */
	public class EmployeeServiceHelper {

		/**
		 * Συνολική υπηρεσία (χωρίς προϋπηρεσία) αφού έχουν αφαιρεθεί ποινές,
		 * μειωμένα ωράρια, κτλ.
		 */
		Integer totalServiceInDays;

		/**
		 * Συνολική υπηρεσία (χωρίς προϋπηρεσία).
		 */
		Integer totalServiceInDaysRaw;

		/**
		 * Συνολικός αριθμός ημερών με ποινές
		 */
		Integer totalPenaltyDays;

		/**
		 * Συνολικός αριθμός ημερών σε άδεια ανεύ αποδοχών
		 */
		Integer totalUnpaidDays;

		public Integer getTotalUnpaidDays() {
			return totalUnpaidDays;
		}

		public void setTotalUnpaidDays(Integer totalUnpaidDays) {
			this.totalUnpaidDays = totalUnpaidDays;
		}

		/**
		 * @return the totalServiceInDays
		 */
		public Integer getTotalServiceInDays() {
			return totalServiceInDays;
		}

		/**
		 * @param totalServiceInDays
		 *            the totalServiceInDays to set
		 */
		public void setTotalServiceInDays(Integer totalServiceInDays) {
			this.totalServiceInDays = totalServiceInDays;
		}

		/**
		 * @return the totalServiceInDaysRaw
		 */
		public Integer getTotalServiceInDaysRaw() {
			return totalServiceInDaysRaw;
		}

		/**
		 * @param totalServiceInDaysRaw
		 *            the totalServiceInDaysRaw to set
		 */
		public void setTotalServiceInDaysRaw(Integer totalServiceInDaysRaw) {
			this.totalServiceInDaysRaw = totalServiceInDaysRaw;
		}

		/**
		 * @return the totalPenaltyDays
		 */
		public Integer getTotalPenaltyDays() {
			return totalPenaltyDays;
		}

		/**
		 * @param totalPenaltyDays
		 *            the totalPenaltyDays to set
		 */
		public void setTotalPenaltyDays(Integer totalPenaltyDays) {
			this.totalPenaltyDays = totalPenaltyDays;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("EmployeeServiceHelper [totalServiceInDays=");
			builder.append(totalServiceInDays);
			builder.append(", totalServiceInDaysRaw=");
			builder.append(totalServiceInDaysRaw);
			builder.append(", totalPenaltyDays=");
			builder.append(totalPenaltyDays);
			builder.append("]");
			return builder.toString();
		}
	}

	/**
	 * Helper class for holding / storing employee's work experience values
	 * 
	 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
	 * @version $Id$
	 */
	public class EmployeeWorkExperienceHelper {

		Integer educationalTotal;

		Integer teachingTotal;

		Integer total;

		/**
		 * @param educationalTotal
		 * @param teachingTotal
		 * @param total
		 */
		public EmployeeWorkExperienceHelper(Integer educationalTotal,
				Integer teachingTotal, Integer total) {
			super();
			this.educationalTotal = educationalTotal;
			this.teachingTotal = teachingTotal;
			this.total = total;
		}

		/**
		 * @return the educationalTotal
		 */
		public Integer getEducationalTotal() {
			return educationalTotal;
		}

		/**
		 * @param educationalTotal
		 *            the educationalTotal to set
		 */
		public void setEducationalTotal(Integer educationalTotal) {
			this.educationalTotal = educationalTotal;
		}

		/**
		 * @return the teachingTotal
		 */
		public Integer getTeachingTotal() {
			return teachingTotal;
		}

		/**
		 * @param teachingTotal
		 *            the teachingTotal to set
		 */
		public void setTeachingTotal(Integer teachingTotal) {
			this.teachingTotal = teachingTotal;
		}

		/**
		 * @return the total
		 */
		public Integer getTotal() {
			return total;
		}

		/**
		 * @param total
		 *            the total to set
		 */
		public void setTotal(Integer total) {
			this.total = total;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("EmployeeWorkExperienceHelper [educationalTotal=");
			builder.append(educationalTotal);
			builder.append(", teachingTotal=");
			builder.append(teachingTotal);
			builder.append(", total=");
			builder.append(total);
			builder.append("]");
			return builder.toString();
		}
	}

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@In(value = "coreSearching")
	protected CoreSearching coreSearching;

	/**
	 * Calculates the sums of an employee's work experience
	 * 
	 * @param employee
	 * @return
	 */
	@Transactional(TransactionPropagationType.REQUIRED)
	public EmployeeWorkExperienceHelper calculateEmployeeWorkExperience(
			Employee employee) {
		Integer educational = coreSearching.getSummedEducationalWorkExperience(
				employee).intValue();
		Integer teaching = coreSearching.getSummedTeachingWorkExperience(
				employee).intValue();
		Integer all = coreSearching.getSummedWorkExperience(employee)
				.intValue();
		return new EmployeeWorkExperienceHelper(educational, teaching, all);
	}

	/**
	 * Επιστρέφει την ημ/νία "έναρξης" της εργασίας κάποιου μόνιμου
	 * εκπαιδευτικού. Η ημ/νία αυτή είναι η ημ/νία διορισμού (ΦΕΚ) ή η ημ/νία
	 * ανάληψης υπηρεσίας στην περίπτωση που η ανάληψη γίνει 30+1 ημέρες μετά το
	 * ΦΕΚ.
	 * 
	 * @param employee
	 * @return
	 */
	public Date computeEmployeeFirstDayOfRegularWork(Employee employee) {
		if (employee != null && employee.getRegularEmployeeInfo() != null) {
			try {
				RegularEmployeeInfo reinfo = employee.getRegularEmployeeInfo();
				Employment curr_employment = employee.getCurrentEmployment();
				Date gofDate = reinfo.getAppointmentGOGDate();
				Date entryIntoServiceDate = curr_employment != null ?curr_employment
						.getEntryIntoServiceDate() : null;
				if (gofDate != null && entryIntoServiceDate != null)
					return CoreUtils.datesDifferenceIn360DaysYear(gofDate,
							entryIntoServiceDate) > 60 ? entryIntoServiceDate
							: gofDate;
				else
					return null;
			} catch (Exception ex) {
				throw new RuntimeException(String.format("exception while handling employee %s with exception %s", employee, ex));
			}
		} else
			return null;
	}

	public Integer calculateEmployeeUnPaidDays(Employee employee,
			Date dateFrom, Date dateTo) {
		
		int totalDaysWithoutPayment = 0; // ημέρες άδειας χωρίς αποδοχές
		
		
		List<String> legacyCodes = Arrays.asList("32", "33", "34", "44");
		
		// fetch all employees leave
		Collection<EmployeeLeave> tempLeaves = coreSearching.getEmployeeLeaves2(
				employee, dateFrom, dateTo);
		
		// filter all leaves with the codes we are interested in
		Collection<EmployeeLeave> leaves = new ArrayList<EmployeeLeave>();
		for (EmployeeLeave leave : tempLeaves) {
			String legacyCode = leave.getEmployeeLeaveType().getLegacyCode();
			if (legacyCodes.contains(legacyCode)) {
				/* this is a leave which we need to take into account */
				
				EmployeeLeave deattachedLeave = new EmployeeLeave(leave);
				
				
				if(deattachedLeave.getDueTo().before(dateFrom) || deattachedLeave.getEstablished().after(dateTo)) {
					// leave ends before dateFrom or leave starts after dateTo
					continue;
				}
				
				
				if(deattachedLeave.getEstablished().before(dateFrom) && deattachedLeave.getDueTo().after(dateFrom)) {
					// leave starts before dateFrom but ends within our period of interest
					deattachedLeave.setEstablished(dateFrom);
				}
				
				if(deattachedLeave.getEstablished().before(dateTo) && deattachedLeave.getDueTo().after(dateTo)) {
					// leave starts before dateTo but ends out of our period
					deattachedLeave.setDueTo(dateTo);
				}
				
				// we need to make adjustments here
				leaves.add(deattachedLeave);
			}
		}
		
		/*
		 * Interate over the employee's leaves (of the given type) and construct
		 * a hash with key the leave's year and value the sum of leave days (360
		 * per year) during this year. Take extra care for leaves spanning
		 * across several years.
		 */
		Map<Integer, Integer> daysOfLeavePerYearHash = new HashMap<Integer, Integer>();
		for (EmployeeLeave leave : leaves) {
			
			
			Calendar leaveStart = DateUtils.toCalendar(leave.getEstablished());
			Calendar leaveStop = DateUtils.toCalendar(leave.getDueTo());
			
			int leaveYearStart = leaveStart.get(Calendar.YEAR);
			int leaveYearStop = leaveStop.get(Calendar.YEAR);
			
			if (leaveYearStop - leaveYearStart == 0) {
				// simple case, leave starts and ends within the year
				Integer durationInYear = daysOfLeavePerYearHash.containsKey(leaveYearStart) ? daysOfLeavePerYearHash.get(leaveYearStart) : 0;
				durationInYear += CoreUtils.datesDifferenceIn360DaysYear(leave.getEstablished(), leave.getDueTo(), true);
				daysOfLeavePerYearHash.put(leaveYearStart, durationInYear);
			} else {
				// more complex case, leave spawns across years
				Date tempLeaveStart = null;
				Date tempLeaveEnd = null;
				for(int yearIndex = leaveYearStart ; yearIndex <= leaveYearStop ; yearIndex++) {
					if(yearIndex == leaveYearStart) {
						// heading of leave
						tempLeaveStart = leave.getEstablished();
						
						// set cal to last day of current year
						Calendar tempCal = Calendar.getInstance();
						tempCal.set(Calendar.DAY_OF_MONTH, 31);
						tempCal.set(Calendar.MONTH, Calendar.DECEMBER);
						tempCal.set(Calendar.YEAR, yearIndex);
						tempLeaveEnd = tempCal.getTime();
						
					} else if(yearIndex == leaveYearStop) {
						// trailing end of leave
						tempLeaveEnd = leave.getDueTo();
						
						// set cal to first day of current year
						Calendar tempCal = Calendar.getInstance();
						tempCal.set(Calendar.DAY_OF_MONTH, 1);
						tempCal.set(Calendar.MONTH, Calendar.JANUARY);
						tempCal.set(Calendar.YEAR, yearIndex);
						tempLeaveStart = tempCal.getTime();
						
					} else {
						// full year
						Calendar tempCal = Calendar.getInstance();
						tempCal.set(Calendar.DAY_OF_MONTH, 1);
						tempCal.set(Calendar.MONTH, Calendar.JANUARY);
						tempCal.set(Calendar.YEAR, yearIndex);
						tempLeaveStart = tempCal.getTime();
						
						tempCal.set(Calendar.DAY_OF_MONTH, 31);
						tempCal.set(Calendar.MONTH, Calendar.DECEMBER);
						tempCal.set(Calendar.YEAR, yearIndex);
						tempLeaveEnd = tempCal.getTime();
						
					}
					
					// update days in hash
					Integer durationInYear = daysOfLeavePerYearHash.containsKey(yearIndex) ? daysOfLeavePerYearHash.get(yearIndex) : 0;
					durationInYear += CoreUtils.datesDifferenceIn360DaysYear(tempLeaveStart, tempLeaveEnd, true);
					daysOfLeavePerYearHash.put(yearIndex, durationInYear);
					
				}
			}
		}
		
		for (Integer year : daysOfLeavePerYearHash.keySet()) {
			Integer _duration = daysOfLeavePerYearHash.get(year);
			totalDaysWithoutPayment += _duration > 30 ? _duration - 30 : 0;
		}
		System.err.println(daysOfLeavePerYearHash);
		System.err.println(totalDaysWithoutPayment);
		return totalDaysWithoutPayment;
		
	}
	

	/**
	 * Calculates an employee's regular service
	 * 
	 * @param employee
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	@Transactional(TransactionPropagationType.REQUIRED)
	public EmployeeServiceHelper calculateRegularEmployeeService(
			Employee employee, Date dateFrom, Date dateTo) {
		
		//DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, new Locale("el", "gr"));

		/* determine employee's total service */
		int totalServiceRaw = CoreUtils.datesDifferenceIn360DaysYear(dateFrom,
				dateTo, true);
		//System.err.println(String.format("Employee '%s' service from '%s' to '%s' is '%d' day(s).", employee, df.format(DateUtils.truncate(dateFrom, Calendar.HOUR)), df.format(DateUtils.truncate(dateTo, Calendar.HOUR)), totalServiceRaw));

		/* handle employee penalties */
		Collection<Penalty> penalties = coreSearching
				.getPenaltyHistory(employee);
		int totalPenaltyDays = 0;
		for (Penalty p : penalties) {
			totalPenaltyDays += CoreUtils.datesDifferenceIn360DaysYear(
					p.getPenaltyStartDate(), p.getPenaltyEndDate(), true);
		}

		/* gh-75 - handle employee leaves */
		int totalDaysWithoutPayment = calculateEmployeeUnPaidDays(employee,
				dateFrom, dateTo);
		/* gh-75 - handle employee leaves */

		/* prepare the return value */
		int totalServiceInDays = totalServiceRaw
				- (totalPenaltyDays + totalDaysWithoutPayment);

		EmployeeServiceHelper returnValue = new EmployeeServiceHelper();
		returnValue.setTotalServiceInDays(totalServiceInDays);
		returnValue.setTotalServiceInDaysRaw(totalServiceRaw);
		returnValue.setTotalPenaltyDays(totalPenaltyDays);
		returnValue.setTotalUnpaidDays(totalDaysWithoutPayment);
		return returnValue;
	}

	@Transactional(TransactionPropagationType.REQUIRED)
	public void updateEmployeeExperience(Employee employee) {
		Date currentDate = DateUtils.truncate(
				new Date(System.currentTimeMillis()), Calendar.DAY_OF_MONTH);
		Date fromDate = computeEmployeeFirstDayOfRegularWork(employee);
		EmployeeInfo employeeInfo = employee.getEmployeeInfo();
		WorkExperienceCalculation.EmployeeWorkExperienceHelper exp = calculateEmployeeWorkExperience(employee);
		WorkExperienceCalculation.EmployeeServiceHelper serviceHelper = calculateRegularEmployeeService(
				employee, fromDate, currentDate);

		if (employeeInfo != null) {
			employeeInfo.setSumOfEducationalExperience(exp
					.getEducationalTotal());
			employeeInfo.setSumOfTeachingExperience(exp.getTeachingTotal());
			employeeInfo.setSumOfExperience(exp.getTotal());
			employeeInfo.setTotalWorkService(serviceHelper
					.getTotalServiceInDays());
			employeeInfo.setSumOfDaysDuringUnpaidLeaves(serviceHelper
					.getTotalUnpaidDays());

			/* handle working hours */
			Employment currentEmployment = employee.getCurrentEmployment();
			if (currentEmployment != null) {
				int currentMandatoryWorkHours = currentEmployment
						.getMandatoryWorkingHours();
				int totalWorkServicePlusExperience = serviceHelper
						.getTotalServiceInDays().intValue()
						+ exp.getEducationalTotal().intValue();
				int mandatoryWorkingHours = calculateEmployeeMandatoryHours(
						totalWorkServicePlusExperience, EmployeeType.REGULAR);
				if (currentMandatoryWorkHours != mandatoryWorkingHours) {
					/* we have a mandatory work hour mismatch ! */
					info("Εmployee's '#0' working hours will be updated from '#1' to '#2",
							employee, currentMandatoryWorkHours,
							mandatoryWorkingHours);
					currentEmployment
							.setMandatoryWorkingHours(mandatoryWorkingHours);
					currentEmployment
							.setFinalWorkingHours(mandatoryWorkingHours);
				}
			}
		}
	}

	/**
	 * Calculates the mandatory working hours for a employee based on the
	 * employee type and his service expressed in days
	 * 
	 * @param totalExperienceInDays
	 * @param employeeType
	 * @return
	 */
	public Integer calculateEmployeeMandatoryHours(
			Integer totalExperienceInDays, EmployeeType employeeType) {
		int years = totalExperienceInDays / 360; // in question
		/*
		 * computation logic from :
		 * http://edu.klimaka.gr/leitoyrgia-sxoleivn/anakoinvseis
		 * /539-school-diafora-vrario-ergasia-symplhrvsh-orariou.html
		 */
		if (years >= 0 && years <= 6)
			return 23;
		else if (years >= 7 && years <= 12)
			return 21;
		else if (years >= 13 && years <= 20)
			return 20;
		else if (years >= 20)
			return 18;
		else
			throw new RuntimeException(
					String.format(
							"failed to compute mandatory work hours for experience '%d' and type '%s'",
							totalExperienceInDays.intValue(),
							employeeType.toString()));

	}

}
