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
@Table(name = "medication", schema = "drones")
public class MedicationEntity {

    @Id
    @Column(name = "medication_code")
    private String medicationCode;
    @Column(name = "medication_name")
    private String medicationName;
    @Column(name = "weight")
    private Double medicationWeight;
    @Column(name = "image_url")
    private String medicationImage;
}
