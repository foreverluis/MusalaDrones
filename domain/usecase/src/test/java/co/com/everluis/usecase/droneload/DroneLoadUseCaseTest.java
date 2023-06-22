package co.com.everluis.usecase.droneload;

import co.com.everluis.model.drone.Drone;
import co.com.everluis.model.drone.gateways.DroneRepository;
import co.com.everluis.model.droneload.gateways.DroneLoadRepository;
import co.com.everluis.model.enums.DroneModel;
import co.com.everluis.model.enums.DroneState;
import co.com.everluis.model.loadedmedication.LoadedMedication;
import co.com.everluis.model.medication.Medication;
import co.com.everluis.model.medication.gateways.MedicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class DroneLoadUseCaseTest {

    @Mock
    private DroneLoadRepository droneLoadRepository;
    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRepository medicationRepository;

    private DroneLoadUseCase droneLoadUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        droneLoadUseCase = new DroneLoadUseCase(medicationRepository,droneRepository, droneLoadRepository);
    }
    @Test
    void loadMedicationAtDroneTest() {
        String droneSerial = "SERIAL400";
        String medicationCode = "MD400";
        int quantity = 3;
        int overWeightQuantity = 20;
        when(medicationRepository.getMedicationByCode(medicationCode))
                .thenReturn(new Medication("Medication 400", 30.0, medicationCode, "MD400"));
        when(droneRepository.getDroneBySerial(droneSerial))
                .thenReturn(new Drone(droneSerial, DroneModel.LightWeight, 500, 80, DroneState.IDLE));

        droneLoadUseCase.loadMedicationAtDrone(droneSerial, medicationCode, quantity);

        Assertions.assertTrue(droneLoadUseCase.loadMedicationAtDrone(droneSerial, medicationCode, quantity));
        Assertions.assertFalse(droneLoadUseCase.loadMedicationAtDrone(droneSerial, medicationCode, overWeightQuantity));
    }

    @Test
    void getLoadedMedicationByDroneSerialTest() {

        String droneSerial = "SERIAL500";
        LoadedMedication loadedMedication1 = LoadedMedication.builder()
                .droneSerial(droneSerial)
                .medicationCode("MD500")
                .medicationName("Medication 500")
                .medicationWeight(30.0)
                .medicationImage("md500.jpg")
                .quantity(10)
                .build();

        LoadedMedication loadedMedication2 = LoadedMedication.builder()
                .droneSerial(droneSerial)
                .medicationCode("MD501")
                .medicationName("Medication 501")
                .medicationWeight(150.0)
                .medicationImage("md501.jpg")
                .quantity(2)
                .build();

        List<LoadedMedication> expectedLoadedMedications = Arrays.asList(loadedMedication1, loadedMedication2);

        when(droneLoadRepository.getLoadedMedicationByDroneSerial(droneSerial))
                .thenReturn(expectedLoadedMedications);

        List<LoadedMedication> loadedMedications = droneLoadUseCase.getLoadedMedicationByDroneSerial(droneSerial);


        Assertions.assertEquals(expectedLoadedMedications.size(), loadedMedications.size());
        Assertions.assertEquals(expectedLoadedMedications, loadedMedications);
    }
}
