package co.com.everluis.usecase.drone;

import co.com.everluis.model.drone.Drone;
import co.com.everluis.model.drone.gateways.DroneRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DroneUseCase {

    private DroneRepository droneRepository;

    public Drone createDrone(Drone drone){
        if(validateDroneData(drone)){
            return droneRepository.saveDrone(drone);
        }
        return null;
    }

    public Drone getDroneBySerial(String droneSerial){
        return droneRepository.getDroneBySerial(droneSerial);
    }

    public List<Drone> checkAvailableDrones(){
        List<Drone> availableDronesForLoading = droneRepository.getAvailableDronesForLoading();
        return availableDronesForLoading;
    }

    public Integer getDroneBatteryCapacity(String droneSerial){
        return getDroneBySerial(droneSerial).getBatteryCapacity();
    }
    private boolean validateDroneData(Drone drone) {
        return true;
    }
}
