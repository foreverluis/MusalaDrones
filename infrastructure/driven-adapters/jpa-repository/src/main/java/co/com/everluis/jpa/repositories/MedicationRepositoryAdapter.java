package co.com.everluis.jpa.repositories;

import co.com.everluis.jpa.entities.MedicationEntity;
import co.com.everluis.model.medication.Medication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MedicationRepositoryAdapter extends CrudRepository<MedicationEntity, Long>,
        QueryByExampleExecutor<MedicationEntity> {

    @Query(value = "select new co.com.everluis.model.medication.Medication (md.medicationName, md.medicationWeight, md.medicationCode, md.medicationImage) from MedicationEntity as md where md.medicationCode = :medicationCode")
    Medication getMedicationByCode(@Param("medicationCode") String medicationCode);
}
