package hospital.repository;

import hospital.entity.Diagnosis;
import hospital.entity.Wards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import hospital.entity.People;

import java.util.List;

public interface PeopleRepository extends CrudRepository<People, Long> {

    @Query(value = "select  d.id, d.name  from People p join diagnosis d where p.id = ?1", nativeQuery = true)
    List<String> getDiagnosisByPeopleId(Long id);

    @Query(value = "select w.id, w.name from people p join wards w where p.id = ?1", nativeQuery = true)
    List<String> getWardsByPeopleId(Long id);

    @Query(value = "select  p.id, p.first_name, p.last_name from people p join wards w  where w.id = ?1", nativeQuery = true)
    List<People> getAllPeopleInWard(Long wardId);

    Long countPeopleByWardId(Wards wardId);
    Long countPeopleByDiagnosisId(Diagnosis diagnosisId);
}
