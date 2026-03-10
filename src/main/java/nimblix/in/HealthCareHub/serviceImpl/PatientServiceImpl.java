package nimblix.in.HealthCareHub.serviceImpl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;
import nimblix.in.HealthCareHub.response.PrescriptionMedicineResponse;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicinesRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public PatientRegistrationResponse registerPatient(PatientRegistrationRequest request) {
        // Check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new PatientRegistrationResponse(false, "Email already exists");
        }

        // Check password match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new PatientRegistrationResponse(false, "Password and Confirm Password do not match");
        }

        // Create User
        User user = new User();
        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(nimblix.in.HealthCareHub.model.Role.PATIENT);
        user.setEnabled(true); // required for login

        userRepository.save(user);

        // Create Patient linked to User
        Patient patient = new Patient();
        patient.setName(request.getFirstName() + " " + request.getLastName());
        patient.setGender(request.getGender());
        patient.setUser(user);

        entityManager.persist(patient);
        return new PatientRegistrationResponse(true, "Registration successful");
    }

    @Override
    public ResponseEntity<Map<String,Object>> getPrescription(Long id) {
        Optional<Prescription> op = prescriptionRepository.findById(id);

        if (op.isPresent()) {
            Map<String,Object> response =new HashMap<>();
            Prescription pr = op.get();
            response.put(HealthCareConstants.STATUS,HealthCareConstants.STATUS_SUCCESS);
            response.put(HealthCareConstants.MESSAGE,HealthCareConstants.FETCHED_SUCCESSFULY);
            response.put(HealthCareConstants.DATA,pr);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String,Object> error =new HashMap<>();
            error.put(HealthCareConstants.STATUS,HealthCareConstants.STATUS_FAILURE);
            error.put(HealthCareConstants.MESSAGE,HealthCareConstants.FETCH_FAILED);
            error.put(HealthCareConstants.DATA,null);
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Map<String,Object>> getPrescriptionMedicines(Long prescription_id) {
        List<PrescriptionMedicines> prescriptions = prescriptionMedicinesRepository.findByPrescriptionId_Id(prescription_id);

        if (!prescriptions.isEmpty()) {
            Map<String,Object> response =new HashMap<>();
            response.put(HealthCareConstants.STATUS,HealthCareConstants.STATUS_SUCCESS);
            response.put(HealthCareConstants.MESSAGE,HealthCareConstants.FETCHED_SUCCESSFULY);
            response.put(HealthCareConstants.DATA,prescriptions);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            Map<String,Object> response =new HashMap<>();
            response.put(HealthCareConstants.STATUS,HealthCareConstants.STATUS_FAILURE);
            response.put(HealthCareConstants.MESSAGE,HealthCareConstants.FETCH_FAILED);
            response.put(HealthCareConstants.DATA,null);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    public String softDeletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

//        patient.setDeleted();   //  Mark as deleted
        patientRepository.save(patient);

        return "Patient soft deleted successfully";
    }

    public Patient savePatient(Patient patient) {
        // TODO Auto-generated method stub
        return patientRepository.save(patient);
    }

    @Override
    public Review addDoctorReview(Long patientId, Long doctorId, String comment, int rating) {
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new RuntimeException("Patient not found"));
//        Doctor doctor = doctorRepository.findById(doctorId)
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//        Review review = Review.builder()
//                .patient(patient)
//                .doctor(doctor)
//                .comment(comment)
//                .rating(rating)
//                .build();
//        // Add review to doctor's review list
//        if (doctor.getReviews() == null) {
//            doctor.setReviews(new ArrayList<>());
//        }
//        doctor.getReviews().add(review);
//        // Saving the doctor will also save the review due to cascade
//        doctorRepository.save(doctor);
//        return review;
        return null;
    }

    @Override
    public List<Review> getDoctorReviews(Long doctorId) {
//        Doctor doctor = doctorRepository.findById(doctorId)
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//        if (doctor.getReviews() == null) {
//            return new ArrayList<>();
//        }
//        return doctor.getReviews();
        return null;
    }

    @Override
    public Review addPatientReview(Long doctorId, Long patientId, String comment, int rating) {
//        Doctor doctor = doctorRepository.findById(doctorId)
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new RuntimeException("Patient not found"));
//        Review review = Review.builder()
//                .doctor(doctor)
//                .patient(patient)
//                .comment(comment)
//                .rating(rating)
//                .build();
//
//        // Add review to patient's review list
//        if (patient.getReviews() == null) {
//            patient.setReviews(new ArrayList<>());
//        }

//        patient.getReviews().add(review);
//        // Saving the patient will also save the review due to cascade
//        patientRepository.save(patient);
//        return review;
        return null;
    }

    @Override
    public List<Review> getPatientReviews(Long patientId) {
//        Patient patient = patientRepository.findById(patientId)
//                                .orElseThrow(() -> new RuntimeException("Patient not found"));
//        if (patient.getReviews() == null) {
//            return new ArrayList<>();
//        }
//        return patient.getReviews();
        return null;
    }

}