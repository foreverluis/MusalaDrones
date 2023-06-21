package co.com.everluis.model.loadedmedication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class LoadedMedication {
    private String droneSerial;
    private String medicationCode;
    private String medicationName;
    private Double medicationWeight;
    private String medicationImage;
    private int quantity;
}
