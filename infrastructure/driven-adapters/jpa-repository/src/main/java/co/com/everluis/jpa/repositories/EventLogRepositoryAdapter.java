package co.com.everluis.jpa.repositories;

import co.com.everluis.jpa.entities.EventLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EventLogRepositoryAdapter extends CrudRepository<EventLogEntity, Long>,
        QueryByExampleExecutor<EventLogEntity> {
}
