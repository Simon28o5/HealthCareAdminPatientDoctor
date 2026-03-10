package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id", length = 50)
    private Long id;

    @Column(name = "prescription_date")
    private String date;

    @Column(name = "Patient_Name")
    private String patientName;

    @Column(name = "Doctor_Name")
    private String doctorName;

    @Column(name = "Diagnosis")
    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "Patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "Doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "prescriptionId", cascade = CascadeType.ALL)
    private List<PrescriptionMedicines> medicines;

    @PrePersist
    protected void onCreate(){
        date= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }
}
