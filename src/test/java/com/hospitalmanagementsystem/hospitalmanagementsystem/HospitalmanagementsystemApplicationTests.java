package com.hospitalmanagementsystem.hospitalmanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hospital.dao.AppointmentRepository;
import com.hospital.dao.DoctorSignUpRepository;
import com.hospital.dao.PatientSignUpRepository;
import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.PatientSignUp;

@SpringBootTest
class  HospitalmanagementsystemApplicationTests {

	@Autowired
	PatientSignUpRepository patientsignupRepository;
	AppointmentRepository appointmentRepository;

	@Test
	public void addPatientSignUp() {
		PatientSignUp patientsignup = new PatientSignUp();
		patientsignup.setP_id(12);
		patientsignup.setP_name("Halim Khan");
		patientsignup.setUsername("halimkhan");
		patientsignup.setPassword("halim");
		patientsignup.setP_gender("Male");
		patientsignup.setP_contact_no("9008233666");
		patientsignupRepository.save(patientsignup);
		assertNotNull(patientsignupRepository.findById(12).get());
	}

	@Test
	public void  AllPatientSignUp() {
		List<PatientSignUp> list = patientsignupRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	public void PatientSignUp() {
		PatientSignUp patientsignup = patientsignupRepository.findById(110).get();
		assertEquals(110,patientsignup.getP_id());
	}
   

	@Autowired
	DoctorSignUpRepository doctorsignupRepository;

	@Test
	public void addDoctorSignUp() {
		Doctor doctorsignup= new Doctor();

		doctorsignup.setD_id(1);
		doctorsignup.setD_name("Dr.Aman");
		doctorsignup.setGender("Male");
		doctorsignup.setUsername("Aman");
		doctorsignup.setPassword("aman");
		doctorsignup.setContact_no("7488996630");
		doctorsignup.setAddress("11/16-1,M.G Road ,Pune");
		doctorsignup.setSpecialization("Medicine");
		doctorsignup.setAccept("Accept");
		doctorsignup.setExperiance("12");
		doctorsignup.setSalary("1200000");
		doctorsignupRepository.save(doctorsignup);
		assertNotNull(doctorsignupRepository.findById(1).get());
	}
	
	@Test
	public void AllDoctorSignUp() {
		List<Doctor> list = doctorsignupRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	public void DoctorSignUp() {
		Doctor doctorsignup = doctorsignupRepository.findById(111).get();
		assertEquals(111, doctorsignup.getD_id());


}

	
	
	@Test
	public void addAppointment() {
		
		Appointment appointment = new Appointment();
		//appointment.setAp_id(1);
		appointment.setP_name("Harish");
		appointment.setD_name("Dr.Aman");
		appointment.setAddress("11/16-1,M.G Road ,Pune");
		appointment.setGender("Male");
		appointment.setAp_date("19-02-2022");
		appointment.setAp_time("5.30pm");
		appointment.setDisease("Fever");
		appointmentRepository.save(appointment);
		assertNotNull(appointmentRepository);
		
		
		
		
	}

	


}