package co.com.everluis.model.eventlog.gateways;

import co.com.everluis.model.eventlog.EventLog;

import java.util.List;

public interface EventLogRepository {
    void saveAllEventLogs(List<EventLog> eventLogList);
}
