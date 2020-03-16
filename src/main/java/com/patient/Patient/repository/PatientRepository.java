package com.patient.Patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Patient.jpaentity.PatientEntity;


public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

	PatientEntity findByPatientId(Integer id);
	
	PatientEntity findByUserId(Integer id);
	
	PatientEntity findBySsn(String ssn);
	

}
