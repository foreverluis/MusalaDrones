package co.com.everluis.model.drone.gateways;

import co.com.everluis.model.drone.Drone;

import java.util.List;

public interface DroneRepository {
    Drone saveDrone(Drone drone);
    Drone getDroneBySerial(String droneSerial);
    List<Drone> getAvailableDronesForLoading();
    List<Drone> getAllDrones();
}
