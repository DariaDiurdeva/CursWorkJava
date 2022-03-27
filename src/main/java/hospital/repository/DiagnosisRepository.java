package hospital.repository;

import org.springframework.data.repository.CrudRepository;
import hospital.entity.Diagnosis;
import java.util.Optional;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Long> {

    Optional<Diagnosis> findDiagnosisByName(String name);
}
