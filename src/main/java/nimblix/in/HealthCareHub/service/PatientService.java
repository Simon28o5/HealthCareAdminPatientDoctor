package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.model.PrescriptionMedicines;
import nimblix.in.HealthCareHub.model.Review;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientRegistrationResponse;
import nimblix.in.HealthCareHub.response.PrescriptionMedicineResponse;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PatientService {

    PatientRegistrationResponse registerPatient(PatientRegistrationRequest request);
//    Simon -------------------------
    ResponseEntity<Map<String,Object>> getPrescription(Long id);
    ResponseEntity<Map<String,Object>> getPrescriptionMedicines(Long prescription_id);
//    ----------------------------------
    Patient savePatient(Patient patient);
    String softDeletePatient(Long id);
    Review addDoctorReview(Long patientId, Long doctorId, String comment, int rating);
    List<Review> getDoctorReviews(Long doctorId);
    Review addPatientReview(Long doctorId, Long patientId, String comment, int rating);
    List<Review> getPatientReviews(Long patientId);
}

