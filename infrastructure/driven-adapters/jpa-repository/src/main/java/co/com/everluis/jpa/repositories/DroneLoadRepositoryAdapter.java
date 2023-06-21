package co.com.everluis.jpa.repositories;

import co.com.everluis.jpa.entities.DroneLoadEntity;
import co.com.everluis.model.loadedmedication.LoadedMedication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface DroneLoadRepositoryAdapter extends CrudRepository<DroneLoadEntity, Long>,
        QueryByExampleExecutor<DroneLoadEntity> {
    @Query(value = "select new co.com.everluis.model.loadedmedication.LoadedMedication(dl.droneSerial, dl.medicationCode, md.medicationName, md.medicationWeight, md.medicationImage, dl.quantity) from MedicationEntity as md, DroneLoadEntity as dl WHERE dl.medicationCode = md.medicationCode and dl.droneSerial = :droneSerial")
    List<LoadedMedication> getLoadedMedicationByDroneSerial(@Param("droneSerial") String droneSerial);
}
