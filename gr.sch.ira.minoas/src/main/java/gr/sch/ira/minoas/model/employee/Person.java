package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.BaseIDModel;
import gr.sch.ira.minoas.model.core.Address;
import gr.sch.ira.minoas.model.core.Telephone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@MappedSuperclass
public abstract class Person extends BaseIDModel {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ADDRESS_ID", nullable = true)
	private Address address;

	@Basic
	@Column(name = "BIG_FAMILY", nullable = true)
	private Boolean bigFamily = Boolean.FALSE;

	@Basic
	@Column(name = "COMMENT", nullable = true, length = 256)
	private String comment;

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DAY", nullable = true)
	private Date dateOfBirth;

	@Basic
	@Column(name = "EMAIL", nullable = true, length = 64)
	private String email;

	@Basic
	@Column(name = "FATHER_NAME", nullable = true, length = 25)
	private String fatherName;

	@Basic
	@Column(name = "FATHER_SURNAME", nullable = true, length = 35)
	private String fatherSurname;
	@Basic
	@Column(name = "FIRST_NAME", nullable = false, length = 25)
	private String firstName;

	@Basic
	@Column(name = "ID_NUMBER", unique = false, nullable = true, length = 10)
	private String idNumber;

	@Basic
	@Column(name = "ID_NUMBER_AUTHORITY", nullable = true, length = 64)
	private String idNumberAuthority;

	/**
	 * Αριθμός Μητρώου ΙΚΑ Μήκος: 7 χαρακτήρες
	 */
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IKA_ID", nullable = true, length = 10)
	private String ikaId;

	@Basic
	@Column(name = "LAST_NAME", nullable = false, length = 35)
	private String lastName;

	@Basic
	@Column(name = "MAN", nullable = true)
	private Boolean man;

	/**
	 * Οικογενειακή Κατάσταση
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "MARITAL_STATUS", length = 30, nullable = true)
	private MaritalStatusType maritalType;

	@Basic
	@Column(name = "MOTHER_NAME", nullable = true, length = 25)
	private String motherName;

	@Basic
	@Column(name = "MOTHER_SURNAME", nullable = true, length = 35)
	private String motherSurname;

	/**
	 * Αριθμός παιδιών
	 */
	@Basic
	@Column(name = "NUMBER_OF_CHILDREN", nullable = true)
	private Integer numberOfChildren;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinTable(name = "MINOAS_EMPLOYEE_TELEPHONES")
	private Collection<Telephone> telephones = new ArrayList<Telephone>();

	@Basic
	@Column(name = "VAT_NUMBER", nullable = true, length = 10)
	private String vatNumber;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor to be used when cloning a person
	 * @param p
	 */
	public Person(Person p) {
		this();
		if(p != null) {
			this.setId(p.getId());
			this.setBigFamily(p.getBigFamily());
			this.setComment(p.getComment());
			this.setDateOfBirth(p.getDateOfBirth());
			this.setEmail(p.getEmail());
			this.setFatherName(p.getFatherName());
			this.setFatherSurname(p.getFatherSurname());
			this.setFirstName(p.getFirstName());
			this.setIdNumber(p.getIdNumber());
			this.setIdNumberAuthority(p.getIdNumberAuthority());
			this.setIkaId(p.getIkaId());
			this.setLastName(p.getLastName());
			this.setMan(p.getMan());
			this.setMaritalType(p.getMaritalType());
			this.setMotherName(p.getMotherName());
			this.setMotherSurname(p.getMotherSurname());
			this.setVatNumber(p.getVatNumber());
			this.setInsertedBy(p.getInsertedBy());
			this.setInsertedOn(p.getInsertedOn());
		}
	}
	
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @return the bigFamily
	 */
	public Boolean getBigFamily() {
		return bigFamily;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @return the fatherSurname
	 */
	public String getFatherSurname() {
		return fatherSurname;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @return the idNumberAuthority
	 */
	public String getIdNumberAuthority() {
		return idNumberAuthority;
	}

	/**
	 * @return the ikaId
	 */
	public String getIkaId() {
		return ikaId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	public Boolean getMan() {
		return isMan();
	}

	/**
	 * @return the maritalType
	 */
	public MaritalStatusType getMaritalType() {
		return maritalType;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @return the motherSurname
	 */
	public String getMotherSurname() {
		return motherSurname;
	}

	/**
	 * @return the numberOfChildren
	 */
	public Integer getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * @return the telephones
	 */
	public Collection<Telephone> getTelephones() {
		return telephones;
	}

	/**
	 * @return the vatNumber
	 */
	public String getVatNumber() {
		return vatNumber;
	}

	/**
	 * @return the man
	 */
	public Boolean isMan() {
		return man;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @param bigFamily
	 *            the bigFamily to set
	 */
	public void setBigFamily(Boolean bigFamily) {
		this.bigFamily = bigFamily;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param fatherName
	 *            the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @param fatherSurname
	 *            the fatherSurname to set
	 */
	public void setFatherSurname(String fatherSurname) {
		this.fatherSurname = fatherSurname;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param idNumber
	 *            the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @param idNumberAuthority
	 *            the idNumberAuthority to set
	 */
	public void setIdNumberAuthority(String idNumberAuthority) {
		this.idNumberAuthority = idNumberAuthority;
	}

	/**
	 * @param ikaId
	 *            the ikaId to set
	 */
	public void setIkaId(String ikaId) {
		this.ikaId = ikaId;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param man
	 *            the man to set
	 */
	public void setMan(Boolean man) {
		this.man = man;
	}

	/**
	 * @param maritalType
	 *            the maritalType to set
	 */
	public void setMaritalType(MaritalStatusType maritalType) {
		this.maritalType = maritalType;
	}

	/**
	 * @param motherName
	 *            the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	/**
	 * @param motherSurname
	 *            the motherSurname to set
	 */
	public void setMotherSurname(String motherSurname) {
		this.motherSurname = motherSurname;
	}

	/**
	 * @param numberOfChildren
	 *            the numberOfChildren to set
	 */
	public void setNumberOfChildren(Integer numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	/**
	 * @param telephones
	 *            the telephones to set
	 */
	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

	/**
	 * @param vatNumber
	 *            the vatNumber to set
	 */
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append(" [");
		if (lastName != null) {
			builder.append("lastName=");
			builder.append(lastName);
			builder.append(", ");
		}
		if (firstName != null) {
			builder.append("fistName=");
			builder.append(firstName);
			builder.append(", ");
		}
		if (fatherName != null) {
			builder.append("fatherName=");
			builder.append(fatherName);
			builder.append(", ");
		}
		if (motherName != null) {
			builder.append("motherName=");
			builder.append(motherName);
		}
		if (idNumber != null) {
			builder.append("idNumber=");
			builder.append(idNumber);
		}
		builder.append("]");
		return builder.toString();
	}

}
