package co.com.everluis.model.droneload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class DroneLoad {

    private String droneSerial;
    private String medicationCode;
    private int quantity;

}
