package co.com.everluis.jpa.adapter;

import co.com.everluis.jpa.entities.EventLogEntity;
import co.com.everluis.jpa.helper.AdapterOperations;
import co.com.everluis.jpa.repositories.EventLogRepositoryAdapter;
import co.com.everluis.model.eventlog.EventLog;
import co.com.everluis.model.eventlog.gateways.EventLogRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EventLogAdapter extends
        AdapterOperations<EventLog, EventLogEntity, Long, EventLogRepositoryAdapter>
        implements EventLogRepository{

    public EventLogAdapter(EventLogRepositoryAdapter repository, ObjectMapper mapper) {
        super(repository, mapper,d -> mapper.map(d, EventLog.EventLogBuilder.class).build());
    }


    @Override
    public void saveAllEventLogs(List<EventLog> eventLogList) {
        super.saveAllEntities(eventLogList);
    }
}
