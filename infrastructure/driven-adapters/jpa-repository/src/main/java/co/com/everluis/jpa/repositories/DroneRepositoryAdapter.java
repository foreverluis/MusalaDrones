package co.com.everluis.jpa.repositories;

import co.com.everluis.jpa.entities.DroneEntity;
import co.com.everluis.model.drone.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface DroneRepositoryAdapter extends CrudRepository<DroneEntity, Long>,
        QueryByExampleExecutor<DroneEntity> {

    @Query(value = "SELECT new co.com.everluis.model.drone.Drone(drone.serialNumber, drone.droneModel, drone.weightLimit, drone.batteryCapacity, drone.droneState) FROM DroneEntity drone WHERE drone.serialNumber = :serial")
    Optional<Drone> getDroneBySerial(@Param("serial") String serial);
    @Query(value = "SELECT new co.com.everluis.model.drone.Drone(drone.serialNumber, drone.droneModel, drone.weightLimit, drone.batteryCapacity, drone.droneState)FROM DroneEntity drone WHERE drone.droneState = co.com.everluis.model.enums.DroneState.IDLE AND drone.batteryCapacity > 24")
    List<Drone> getAvailableDronesForLoading();
}
