package co.com.everluis.usecase.drone;

import co.com.everluis.model.drone.Drone;
import co.com.everluis.model.drone.gateways.DroneRepository;
import co.com.everluis.model.enums.DroneModel;
import co.com.everluis.model.enums.DroneState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class DroneUseCaseTest {

    @Mock
    private DroneRepository droneRepository;

    private DroneUseCase droneUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        droneUseCase = new DroneUseCase(droneRepository);
    }

    @Test
    void createDroneTest(){
        Drone rightDrone = Drone.builder()
                .serialNumber("SERIAL100")
                .droneModel(DroneModel.CruiserWeight)
                .weightLimit(400)
                .batteryCapacity(100)
                .droneState(DroneState.IDLE)
                .build();
        Drone wrongDrone = Drone.builder()
                .serialNumber("SERIAL101")
                .droneModel(DroneModel.HeavyWeight)
                .weightLimit(501)
                .batteryCapacity(99)
                .droneState(DroneState.LOADED)
                .build();

        when(droneRepository.saveDrone(rightDrone)).thenReturn(rightDrone);
        Drone savedDrone = droneUseCase.createDrone(rightDrone);

        Drone nullDrone = droneUseCase.createDrone(wrongDrone);
        Assertions.assertNull(nullDrone);

        Assertions.assertNotNull(savedDrone);
        Assertions.assertEquals(rightDrone.getSerialNumber(),savedDrone.getSerialNumber());
        Assertions.assertEquals(rightDrone.getDroneState(),savedDrone.getDroneState());

    }

    @Test
    void getDroneBySerialTest(){
        String droneSerial = "SERIAL200";
        String wrongSerial = "NO";
        Drone rightDrone = Drone.builder()
                .serialNumber(droneSerial)
                .droneModel(DroneModel.LightWeight)
                .weightLimit(100)
                .batteryCapacity(65)
                .droneState(DroneState.LOADING)
                .build();
        when(droneRepository.getDroneBySerial(droneSerial)).thenReturn(rightDrone);
        when(droneRepository.getDroneBySerial(wrongSerial)).thenReturn(null);

        Drone retrievedDrone = droneUseCase.getDroneBySerial(droneSerial);
        Drone nullDrone = droneUseCase.getDroneBySerial(wrongSerial);

        Assertions.assertNotNull(retrievedDrone);
        Assertions.assertEquals(droneSerial, retrievedDrone.getSerialNumber());
        Assertions.assertNull(nullDrone);
    }

    @Test
    void checkAvailableDronesTest(){

        Drone drone1 = Drone.builder()
                .serialNumber("SERIAL300")
                .droneModel(DroneModel.LightWeight)
                .weightLimit(100)
                .batteryCapacity(55)
                .droneState(DroneState.IDLE)
                .build();
        Drone drone2 = Drone.builder()
                .serialNumber("SERIAL301")
                .droneModel(DroneModel.CruiserWeight)
                .weightLimit(400)
                .batteryCapacity(99)
                .droneState(DroneState.RETURNING)
                .build();
        Drone drone3 = Drone.builder()
                .serialNumber("SERIAL302")
                .droneModel(DroneModel.HeavyWeight)
                .weightLimit(500)
                .batteryCapacity(23)
                .droneState(DroneState.IDLE)
                .build();
        List<Drone> expectedDrones = Arrays.asList(drone1, drone3);
        when(droneRepository.getAvailableDronesForLoading()).thenReturn(expectedDrones);


        List<Drone> availableDrones = droneUseCase.checkAvailableDrones();

        Assertions.assertEquals(expectedDrones.size(), availableDrones.size());
        Assertions.assertEquals(expectedDrones, availableDrones);
    }

}
