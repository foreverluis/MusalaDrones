package co.com.everluis.model.medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Medication {

    private String medicationName;
    private Double medicationWeight;
    private String medicationCode;
    private String medicationImage;
}
