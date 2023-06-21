package co.com.everluis.model.medication;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(toBuilder = true)
public class Medication {

    private String medicationName;
    private Double medicationWeight;
    private String medicationCode;
    private String medicationImage;
}
