package co.com.everluis.jpa.adapter;

import co.com.everluis.jpa.entities.MedicationEntity;
import co.com.everluis.jpa.helper.AdapterOperations;
import co.com.everluis.jpa.repositories.MedicationRepositoryAdapter;
import co.com.everluis.model.medication.Medication;
import co.com.everluis.model.medication.gateways.MedicationRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;


@Repository
public class MedicationAdapter extends
        AdapterOperations<Medication, MedicationEntity, Long, MedicationRepositoryAdapter>
        implements MedicationRepository {
    public MedicationAdapter(MedicationRepositoryAdapter repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Medication.MedicationBuilder.class).build());
    }

    @Override
    public Medication getMedicationByCode(String medicationCode) {
        return repository.getMedicationByCode(medicationCode);
    }
}
