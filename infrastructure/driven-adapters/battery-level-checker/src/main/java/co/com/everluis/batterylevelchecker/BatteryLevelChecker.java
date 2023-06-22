package co.com.everluis.batterylevelchecker;

import co.com.everluis.model.drone.Drone;
import co.com.everluis.model.drone.gateways.DroneRepository;
import co.com.everluis.model.eventlog.EventLog;
import co.com.everluis.model.eventlog.gateways.EventLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class BatteryLevelChecker {

    private final DroneRepository droneRepository;
    private final EventLogRepository eventLogRepository;
    @Scheduled(fixedDelay = 600000)
    public void checkDronesBatteryLevel(){
        List<Drone> droneList = droneRepository.getAllDrones();
        List<EventLog> eventLogList = new ArrayList<>();
        for (Drone drone : droneList) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            EventLog eventLog = EventLog.builder()
                    .eventID(timestamp + " - " + drone.getSerialNumber())
                    .eventType("Battery Level Log")
                    .eventEntity("Drone")
                    .eventDetails("Battery level: " + drone.getBatteryCapacity())
                    .build();
            eventLogList.add(eventLog);
        }
        eventLogRepository.saveAllEventLogs(eventLogList);
    }
}
