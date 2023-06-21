package co.com.everluis.usecase.droneload;

import co.com.everluis.model.drone.gateways.DroneRepository;
import co.com.everluis.model.droneload.DroneLoad;
import co.com.everluis.model.droneload.gateways.DroneLoadRepository;
import co.com.everluis.model.medication.gateways.MedicationRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class DroneLoadUseCase {

    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;
    private final DroneLoadRepository droneLoadRepository;

    public Boolean loadMedicationAtDrone(String droneSerial, String medicationCode, int quantity){
        if (droneLoadOverWeight(droneSerial,medicationCode,quantity)){
            droneLoadRepository.saveDroneLoad(new DroneLoad(droneSerial,medicationCode,quantity));
            return true;
        }
        return false;
    }

    private boolean droneLoadOverWeight(String droneSerial, String medicationCode, int quantity) {
        return (medicationRepository.getMedicationByCode(medicationCode).getMedicationWeight() * quantity)
            > droneRepository.getDroneBySerial(droneSerial).getWeightLimit();
    }
}
