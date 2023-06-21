package co.com.everluis.jpa.repositories;

import co.com.everluis.jpa.entities.MedicationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MedicationRepositoryAdapter extends CrudRepository<MedicationEntity, Long>,
        QueryByExampleExecutor<MedicationEntity> {

}
