package co.com.everluis.usecase.drone;

import co.com.everluis.model.drone.Drone;
import co.com.everluis.model.drone.gateways.DroneRepository;
import co.com.everluis.model.enums.DroneModel;
import co.com.everluis.model.enums.DroneState;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.List;

@RequiredArgsConstructor
public class DroneUseCase {

    private final DroneRepository droneRepository;

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
        return droneRepository.getAvailableDronesForLoading();
    }

    private boolean validateBatteryCapacity(int batteryCapacity){
        return batteryCapacity < 101 && batteryCapacity >= 0;
    }
    private boolean validateState(DroneState droneState) {
        return EnumSet.allOf(DroneState.class).contains(droneState);
    }

    private boolean validateModel(DroneModel droneModel) {
        return EnumSet.allOf(DroneModel.class).contains(droneModel);
    }

    private boolean validateSerialLength(String serialNumber) {
        return serialNumber.length() > 0 && serialNumber.length() < 101;
    }

    private boolean validateWeightLimit(int weightLimit) {
        return weightLimit > 0 && weightLimit < 501;
    }

    private boolean validateDroneData(Drone drone) {
        return validateModel(drone.getDroneModel()) && validateState(drone.getDroneState())
                && validateSerialLength(drone.getSerialNumber()) && validateWeightLimit(drone.getWeightLimit())
                && validateBatteryCapacity(drone.getBatteryCapacity());
    }
}
