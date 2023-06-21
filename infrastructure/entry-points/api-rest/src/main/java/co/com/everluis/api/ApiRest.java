package co.com.everluis.api;
import co.com.everluis.model.drone.Drone;
import co.com.everluis.model.droneload.DroneLoad;
import co.com.everluis.model.enums.DroneState;
import co.com.everluis.model.loadedmedication.LoadedMedication;
import co.com.everluis.model.medication.Medication;
import co.com.everluis.usecase.drone.DroneUseCase;
import co.com.everluis.usecase.droneload.DroneLoadUseCase;
import co.com.everluis.usecase.medication.MedicationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final DroneUseCase droneUseCase;
    private final DroneLoadUseCase droneLoadUseCase;
    private final MedicationUseCase medicationUseCase;


    @PostMapping(path = "/drone")
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone) {

        if (droneUseCase.getDroneBySerial(drone.getSerialNumber()) == null && droneUseCase.createDrone(drone) != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(droneUseCase.getDroneBySerial(drone.getSerialNumber()));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(drone);
        }

    }

    @GetMapping(path = "/drone/check-available-drones")
    public ResponseEntity<List<Drone>> getAvailableDrones(){
        List<Drone> availableDrones = droneUseCase.checkAvailableDrones();
        if (availableDrones.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(availableDrones);
    }

    @GetMapping(path = "/drone/{droneSerial}/batteryLevel")
    public ResponseEntity<Integer> getDroneBatteryCapacity(@PathVariable String droneSerial) {
        Drone drone = droneUseCase.getDroneBySerial(droneSerial);
        if (drone != null){
            return ResponseEntity.ok(drone.getBatteryCapacity());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/drone/{droneSerial}/load-medication")
    public ResponseEntity<DroneLoad> loadDroneMedication(@PathVariable String droneSerial, @RequestParam String medicationCode, @RequestParam int quantity){
        Drone drone = droneUseCase.getDroneBySerial(droneSerial);
        Medication medication = medicationUseCase.getMedicationByCode(medicationCode);
        if (drone == null || medication == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else if (Boolean.TRUE.equals(droneLoadUseCase.loadMedicationAtDrone(droneSerial,medicationCode,quantity))){
            drone.setDroneState(DroneState.LOADED);
            droneUseCase.createDrone(drone);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(path = "/drone/{droneSerial}/loaded-medications")
    public ResponseEntity<List<LoadedMedication>> getLoadedMedications(@PathVariable String droneSerial){
        List<LoadedMedication> loadedMedications = droneLoadUseCase.getLoadedMedicationByDroneSerial(droneSerial);
        if (loadedMedications.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(loadedMedications);

    }



}
