package hospital.repository;

import hospital.entity.Diagnosis;
import hospital.entity.Wards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import hospital.entity.People;

import java.util.List;

public interface PeopleRepository extends CrudRepository<People, Long> {

    @Query(value = "select  d.id, d.name  from diagnosis d join people p on d.id = p.diagnosis_id where p.id = ?1",nativeQuery = true)
    List<String> getDiagnosisByPeopleId(Long id);

    @Query(value = "select w.id, w.name from wards w join people p on w.id = p.ward_id where p.id= ?1", nativeQuery = true)
    List<String> getWardsByPeopleId(Long id);

    @Query(value = "select  p.id, first_name, last_name, father_name from people p join wards w on p.ward_id = w.id where w.id = ?1",nativeQuery = true)
    List<String> getAllPeopleInWard(Long wardId);


    @Query("select count(p) from People p where p.ward_id = ?1")
    Long countPeopleByWard_id(Wards ward_id);
    @Query(value = "select count(p.id) from people p join diagnosis d  on p.diagnosis_id = d.id where d.name = ?1", nativeQuery = true)
    Long countPeopleByDiagnosisName(String name);
}
