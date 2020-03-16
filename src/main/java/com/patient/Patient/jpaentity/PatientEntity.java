package com.patient.Patient.jpaentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Table(name = "Patient")
public class PatientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PatientID")
	private Integer patientId;
	@Column(name = "UserId")
	private Integer userId;
	@Column(name = "CreatedAt")
	private String createdAt;
	@Column(name = "UpdatedAt")
	private String updatedAt;
	@Column(name = "Active")
	private String active;
	@Column(name = "Ssn")
	private String ssn;
	@Column(name = "Dob")
	private String dob;
	@Column(name = "Gender")
	private String gender;
	@Column(name = "Address")
	private String address;
	@Column(name = "City")
	private String city;
	@Column(name = "State")
	private String state;
	@Column(name = "ZipCode")
	private String zipCode;
	@Column(name = "InsuranceName")
	private String insuranceName;
	@Column(name = "PlanNo")
	private String planNo;
	@Column(name = "CoPayAmount")
	private String coPayAmount;
	@Transient
	private String errorMessage;
	
	
	
	public PatientEntity(Integer patientId, Integer userId, String createdAt, String updatedAt, String active,
			String ssn, String dob, String gender, String address, String city, String state, String zipCode,
			String insuranceName, String planNo, String coPayAmount) {
		super();
		this.patientId = patientId;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.active = active;
		this.ssn = ssn;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.insuranceName = insuranceName;
		this.planNo = planNo;
		this.coPayAmount = coPayAmount;
	}
	
	
	public PatientEntity() {
	}


	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public String getCoPayAmount() {
		return coPayAmount;
	}
	public void setCoPayAmount(String coPayAmount) {
		this.coPayAmount = coPayAmount;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
