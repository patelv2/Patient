package com.patient.Patient.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.patient.Patient.jpaentity.PatientEntity;
import com.patient.Patient.repository.PatientRepository;



@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
	
	final String secretKey = "helllooo!!!!";

	
	public PatientEntity getPatient(Integer userId) {
		PatientEntity patientEntity = patientRepository.findByUserId(userId);
		if(patientEntity != null && patientEntity.getActive().equalsIgnoreCase("Y")) {
			patientEntity.setSsn(AES.decrypt(patientEntity.getSsn(), secretKey));
		
		}else {
			patientEntity = null;
		}
		
		return patientEntity;
		
	}
	
	public PatientEntity getPatientBySsn(String ssn) {
		PatientEntity existingPatientEntity = patientRepository.findBySsn(AES.encrypt(ssn, secretKey));
		if(existingPatientEntity != null && existingPatientEntity.getActive().equalsIgnoreCase("Y")) {
			existingPatientEntity.setSsn(AES.decrypt(existingPatientEntity.getSsn(), secretKey));
		
		}else {
			existingPatientEntity = null;
		}
		
		return existingPatientEntity;
		
	}
	
	public List<PatientEntity> getPatients(Integer doctorId) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("UserId", doctorId);

		return jdbcTemplate.query(
				"select PatientId,CreatedAt,UpdatedAt,UserId,Active,Ssn,Dob,Gender,Address,City,State, "
						+ "ZipCode,InsuranceName,PlanNo,CoPayAmount "
						+ "from Patient where PatientId in (select distinct(PatientId) from Prescription where DoctorId = :UserId)",
				mapSqlParameterSource,
				(rs, rowNum) -> new PatientEntity(rs.getInt("PatientID"), rs.getInt("UserId"),
						rs.getString("CreatedAt"), rs.getString("UpdatedAt"), rs.getString("Active"),
						AES.decrypt(rs.getString("Ssn"), secretKey), rs.getString("Dob"), rs.getString("Gender"), rs.getString("Address"),
						rs.getString("City"), rs.getString("State"), rs.getString("ZipCode"),
						rs.getString("InsuranceName"), rs.getString("PlanNo"), rs.getString("CoPayAmount")));

	}
	
	public PatientEntity save(PatientEntity patientEntity) {
		PatientEntity existingPatientEntity = patientRepository.findBySsn(AES.encrypt(patientEntity.getSsn(), secretKey));
		if (existingPatientEntity == null) {
			Date date = new Date();
			patientEntity.setCreatedAt(dtf.format(date));
			patientEntity.setUpdatedAt(dtf.format(date));
			patientEntity.setActive("Y");
			patientEntity.setSsn(AES.encrypt(patientEntity.getSsn(), secretKey));
			patientRepository.save(patientEntity);
			patientEntity.setSsn(AES.decrypt(patientEntity.getSsn(), secretKey));
		}
		else {
			patientEntity = new PatientEntity();
			patientEntity.setErrorMessage("Patient Already Exist");
		}
    	return patientEntity;
    }

	
	public PatientEntity delete(Integer patientId) {
		Date date = new Date(); 
		PatientEntity patientEntity = patientRepository.findByPatientId(patientId);
		patientEntity.setActive("N");
		patientEntity.setCreatedAt(dtf.format(date));
		patientEntity.setUpdatedAt(dtf.format(date));
		patientRepository.save(patientEntity);
		return patientEntity;
	}
	
	
	public Boolean update(PatientEntity user, Integer patientId) {
		Date date = new Date(); 
		Boolean isUserUpdated = false;
		PatientEntity patientEntity = patientRepository.findByPatientId(patientId);
		if (patientEntity != null) {
			if (user.getAddress() != null) {
				patientEntity.setAddress(user.getAddress());
			}
			if (user.getCity() != null) {
				patientEntity.setCity(user.getCity());
			}
			if (user.getState() != null) {
				patientEntity.setState(user.getState());
			}
			if (user.getZipCode() != null) {
				patientEntity.setZipCode(user.getZipCode());
			}
			if (user.getGender() != null) {
				patientEntity.setGender(user.getGender());
			}
			if (user.getCoPayAmount() != null) {
				patientEntity.setCoPayAmount(user.getCoPayAmount());
			}
			if (user.getInsuranceName() != null) {
				patientEntity.setInsuranceName(user.getInsuranceName());
			}
			if (user.getPlanNo() != null) {
				patientEntity.setPlanNo(user.getPlanNo());
			}
//			if (user.getSsn() != null) {
//				patientEntity.setSsn(AES.encrypt(user.getSsn(), secretKey));
//			}
			if (user.getDob() != null) {
				patientEntity.setDob(user.getDob());
			}
			patientEntity.setCreatedAt(dtf.format(date));
			patientEntity.setUpdatedAt(dtf.format(date));
			patientEntity.setActive("Y");
			patientRepository.save(patientEntity);
			return !isUserUpdated;
		} else {
			return isUserUpdated;
		}
	}

	public PatientEntity getPatientInfoBasedOnPatientId(Integer patientId) {
		PatientEntity patientEntity = patientRepository.findByPatientId(patientId);
		if(patientEntity != null && patientEntity.getActive().equalsIgnoreCase("Y")) {
			patientEntity.setSsn(AES.decrypt(patientEntity.getSsn(), secretKey));
		
		}else {
			patientEntity = null;
		}
		return patientEntity;
	}
	
}

