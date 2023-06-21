package co.com.everluis.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "drone_load", schema = "drones")
public class DroneLoadEntity {

    @Id
    @Column(name = "drone_id")
    private String droneSerial;
    @Column(name = "medication_id")
    private String medicationCode;
    @Column(name = "quantity")
    private int quantity;
}
