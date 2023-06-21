package co.com.everluis.jpa.adapter;

import co.com.everluis.jpa.entities.DroneEntity;
import co.com.everluis.jpa.helper.AdapterOperations;
import co.com.everluis.jpa.repositories.DroneRepositoryAdapter;
import co.com.everluis.model.drone.Drone;
import co.com.everluis.model.drone.gateways.DroneRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DroneAdapter extends
        AdapterOperations<Drone, DroneEntity, Long, DroneRepositoryAdapter>
        implements DroneRepository {

    public DroneAdapter(DroneRepositoryAdapter repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d,Drone.DroneBuilder.class).build());
    }

    @Override
    public Drone saveDrone(Drone drone) {
        return super.save(drone);
    }

    @Override
    public Drone getDroneBySerial(String droneSerial) {
        return repository.getDroneBySerial(droneSerial).orElse(null);
    }

    @Override
    public List<Drone> getAvailableDronesForLoading() {
        return repository.getAvailableDronesForLoading();
    }
}
