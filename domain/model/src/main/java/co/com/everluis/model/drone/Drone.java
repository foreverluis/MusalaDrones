package co.com.everluis.model.drone;
import co.com.everluis.model.enums.DroneModel;
import co.com.everluis.model.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class Drone {
    private String serialNumber;
    private DroneModel droneModel;
    private int weightLimit;
    private int batteryCapacity;
    private DroneState droneState;
}
