package co.com.everluis.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "event_log", schema = "drones")
public class EventLogEntity {

    @Id
    @Column(name = "event_id")
    private String eventID;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "event_entity")
    private String eventEntity;
    @Column(name = "event_details")
    private String eventDetails;
}
