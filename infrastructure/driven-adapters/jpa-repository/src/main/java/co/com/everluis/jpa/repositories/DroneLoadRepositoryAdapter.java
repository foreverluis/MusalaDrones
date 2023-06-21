package co.com.everluis.jpa.repositories;

import co.com.everluis.jpa.entities.DroneLoadEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface DroneLoadRepositoryAdapter extends CrudRepository<DroneLoadEntity, Long>,
        QueryByExampleExecutor<DroneLoadEntity> {

}
