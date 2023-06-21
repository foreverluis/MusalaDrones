package co.com.everluis.api;
import co.com.everluis.model.drone.Drone;
import co.com.everluis.usecase.drone.DroneUseCase;
import co.com.everluis.usecase.droneload.DroneLoadUseCase;
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


    @PostMapping(path = "/drone")
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone) {
        droneUseCase.createDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(droneUseCase.getDroneBySerial(drone.getSerialNumber()));
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

}
