package co.com.everluis.usecase.medication;

import co.com.everluis.model.medication.Medication;
import co.com.everluis.model.medication.gateways.MedicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class MedicationUseCaseTest {

    @Mock
    private MedicationRepository medicationRepository;

    private MedicationUseCase medicationUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        medicationUseCase = new MedicationUseCase(medicationRepository);
    }

    @Test
    void getMedicationByCodeTest(){
        String medicationCode = "MD600";
        String wrongMedicationCode = "MD601";
        Medication medication = Medication.builder()
                .medicationName("Medication 601")
                .medicationWeight(450.0)
                .medicationCode(medicationCode)
                .medicationImage("MD601.jpg")
                .build();
        when(medicationRepository.getMedicationByCode(medicationCode)).thenReturn(medication);
        when(medicationRepository.getMedicationByCode(wrongMedicationCode)).thenReturn(null);

        Medication retrievedMedication = medicationUseCase.getMedicationByCode(medicationCode);
        Medication nullMedication = medicationUseCase.getMedicationByCode(wrongMedicationCode);

        Assertions.assertNotNull(retrievedMedication);
        Assertions.assertEquals(medicationCode, retrievedMedication.getMedicationCode());
        Assertions.assertNull(nullMedication);
    }
}
