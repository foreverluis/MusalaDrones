package co.com.everluis.model.droneload.gateways;

import co.com.everluis.model.droneload.DroneLoad;
import co.com.everluis.model.loadedmedication.LoadedMedication;

import java.util.List;

public interface DroneLoadRepository {
    DroneLoad saveDroneLoad(DroneLoad droneLoad);
    List<LoadedMedication> getLoadedMedicationByDroneSerial(String droneSerial);
}
