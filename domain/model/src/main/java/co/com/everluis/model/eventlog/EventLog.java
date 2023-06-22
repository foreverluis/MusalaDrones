package co.com.everluis.model.eventlog;
import lombok.*;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class EventLog {
    private String eventID;
    private String eventType;
    private String eventEntity;
    private String eventDetails;
}
