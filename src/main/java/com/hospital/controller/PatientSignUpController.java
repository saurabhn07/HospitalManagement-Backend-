package com.hospital.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dao.PatientSignUpRepository;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.model.Doctor;
import com.hospital.model.PatientSignUp;
import com.hospital.service.PatientSignUpService;
@CrossOrigin(origins="*")
@RequestMapping("/api/v1")
@RestController
public class PatientSignUpController {
	@Autowired
	private PatientSignUpRepository patientsignuprepository;
	PatientSignUpService patientsignupService;

//http://localhost:8080/api/v1/getAllPatients
	@GetMapping("/patients")
	public List<PatientSignUp> getPatients() {
		//List<PatientSignUp> patientsignupList = patientsignupService.fetchPatients();
		
		return patientsignuprepository.findAll();

	}

	// http://localhost:8080/api/v1/getPatient/1
	@GetMapping("/patient/{p_id}")
	public ResponseEntity<PatientSignUp> getPatientById(@PathVariable("p_id") int p_id)
			throws ResourceNotFoundException {
		PatientSignUp psup = patientsignupService.getPatient(p_id);
		return ResponseEntity.ok().body(psup);
	}

	// http://localhost:8088/api/v1/patient
//	@PostMapping("/patients")
//	public PatientSignUp addPatient(@RequestBody PatientSignUp p) {
//
//		//PatientSignUp patient = patientsignupService.savePatient(p);
//		return patientsignuprepository.save(p);
//
//		// return new ResponseEntity<>("Employee added successsfully", HttpStatus.OK);
//		//return patient;
//	}
	@PostMapping("/patients")
	public PatientSignUp addPatientSignUp(@RequestBody PatientSignUp  p) {
		PatientSignUp patientsignup = patientsignuprepository.save(p);
		return patientsignup;
	}


	// http://localhost:8080/api/v1/updatePatient/2
//	@PutMapping("/patients/{p_id}")
//	public ResponseEntity<PatientSignUp> updatePatient(@PathVariable("p_id") int p_id,
//			@RequestBody PatientSignUp PatientDetails) throws ResourceNotFoundException {
//		PatientSignUp p = patientsignupService.getPatient(p_id);
//		p.setP_name(PatientDetails.getP_name());
//		p.setUsername(PatientDetails.getUsername());
//	
//		p.setP_gender(PatientDetails.getP_gender());
//		p.setP_contact_no(PatientDetails.getP_contact_no());
//		p.setPassword(PatientDetails.getPassword());
//		final PatientSignUp updatedPatient = patientsignupService.savePatient(p);
//		return ResponseEntity.ok(updatedPatient);
//	}

	@PutMapping("/patients/{p_id}")
	public ResponseEntity<PatientSignUp> updatePatientSignUp(@PathVariable int p_id, @RequestBody PatientSignUp patients) throws ResourceNotFoundException
	{
		PatientSignUp p= patientsignuprepository.findById(p_id).orElseThrow(() ->new ResourceNotFoundException("Patients not exist with id :" +p_id));
		p.setP_id(patients.getP_id());
		p.setP_name(patients.getP_name());
		p.setP_gender(patients.getP_gender());
		p.setUsername(patients.getUsername());
		p.setPassword(patients.getPassword());
		p.setP_contact_no(patients.getP_contact_no());
//		p.setAddress(patients.getAddress());
//		.setSpecialization(doctor.getSpecialization());
		 PatientSignUp updatedPatientsignup = patientsignuprepository.save(p);
		return ResponseEntity.ok(updatedPatientsignup);
	}
	
//http://localhost:8080/api/v1/deletePatient/1
//	@DeleteMapping(value = "/patients/{p_id}")
//	public ResponseEntity<Object> deletePatient(@PathVariable("p_id") int p_id) {
//
//		patientsignupService.deletePatient(p_id);
//		return new ResponseEntity<>("Patient deleted successsfully", HttpStatus.OK);
//	}
//	
	@DeleteMapping(value = "/patients/{p_id}")
	public ResponseEntity< Map<String, Boolean>> deletePatient(@PathVariable int p_id ) throws ResourceNotFoundException
	{
	PatientSignUp p= patientsignuprepository.findById(p_id)
				.orElseThrow(() ->new ResourceNotFoundException("patients not exist with id :" +p_id));
		patientsignuprepository.delete(p);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("deleted", Boolean.TRUE);
		 return ResponseEntity.ok(response);
	}
}