package co.com.everluis.jpa.entities;

import co.com.everluis.model.enums.DroneModel;
import co.com.everluis.model.enums.DroneState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "drone", schema = "drones")
public class DroneEntity {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private DroneModel droneModel;
    @Column(name = "weight_limit")
    private int weightLimit;
    @Column(name = "battery_capacity")
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState droneState;
}
