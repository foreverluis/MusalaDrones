package co.com.everluis.usecase.medication;

import co.com.everluis.model.medication.Medication;
import co.com.everluis.model.medication.gateways.MedicationRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class MedicationUseCase {

    private final MedicationRepository medicationRepository;

    public Medication getMedicationByCode(String medicationCode){
        return medicationRepository.getMedicationByCode(medicationCode);
    }
}
