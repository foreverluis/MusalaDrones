package co.com.everluis.jpa.adapter;

import co.com.everluis.jpa.entities.DroneLoadEntity;
import co.com.everluis.jpa.helper.AdapterOperations;
import co.com.everluis.jpa.repositories.DroneLoadRepositoryAdapter;
import co.com.everluis.model.droneload.DroneLoad;
import co.com.everluis.model.droneload.gateways.DroneLoadRepository;
import co.com.everluis.model.loadedmedication.LoadedMedication;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DroneLoadAdapter extends
        AdapterOperations<DroneLoad, DroneLoadEntity,Long, DroneLoadRepositoryAdapter>
        implements DroneLoadRepository {
    public DroneLoadAdapter(DroneLoadRepositoryAdapter repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, DroneLoad.DroneLoadBuilder.class).build());
    }

    @Override
    public DroneLoad saveDroneLoad(DroneLoad droneLoad) {
        return super.save(droneLoad);
    }

    @Override
    public List<LoadedMedication> getLoadedMedicationByDroneSerial(String droneSerial) {
        return repository.getLoadedMedicationByDroneSerial(droneSerial);
    }
}
