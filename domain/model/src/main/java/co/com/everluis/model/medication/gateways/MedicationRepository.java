package co.com.everluis.model.medication.gateways;

import co.com.everluis.model.medication.Medication;

public interface MedicationRepository {
    Medication getMedicationByCode(String medicationCode);
}
