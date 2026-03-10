package nimblix.in.HealthCareHub.repository;

import org.springframework.stereotype.Repository;
import nimblix.in.HealthCareHub.model.PrescriptionMedicines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicines,String> {
    List<PrescriptionMedicines> findByPrescriptionId_Id(Long id);
}
