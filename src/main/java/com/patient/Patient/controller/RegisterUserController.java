 package com.patient.Patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Patient.jpaentity.PatientEntity;
import com.patient.Patient.service.PatientService;



@RestController
@RequestMapping("/api")
public class RegisterUserController {
	
	@Autowired 
	PatientService patientService;
	
	@GetMapping(path = "/user/{id}")
	public PatientEntity getPatient(@PathVariable("id") Integer userId) {
		return patientService.getPatient(userId);
	}
	
	@GetMapping(path = "/patient/{id}")
	public PatientEntity getPatientInfofromPatient(@PathVariable("id") Integer patientId) {
		return patientService.getPatientInfoBasedOnPatientId(patientId);
	}
	
	@GetMapping(path = "/patients")
	public List<PatientEntity> getPatients(@RequestParam("doctorId") Integer doctorId) {
		return patientService.getPatients(doctorId);
	}
	
	@GetMapping(path = "/patient")
	public PatientEntity getPatientsBySsn(@RequestParam("ssn") String ssn) {
		return patientService.getPatientBySsn(ssn);
	}
	
	@PostMapping(path = "/patient")
	public PatientEntity post(@RequestBody PatientEntity patientEntity) {
		return patientService.save(patientEntity);
	}
	
	@PatchMapping("/patient/{id}")
	public ResponseEntity<PatientEntity> update(@RequestBody PatientEntity patient, @PathVariable("id") Integer patientId) {
		Boolean isUserUpdated = patientService.update(patient,patientId);
		if (isUserUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(patient);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@DeleteMapping(path = "/patient/{id}")
	public ResponseEntity delete(@PathVariable("id") Integer patientId) {
		PatientEntity patientEntity = patientService.delete(patientId);
		if (patientEntity.getActive().equalsIgnoreCase("N")) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
