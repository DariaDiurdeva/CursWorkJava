package hospital.repository;

import org.springframework.data.repository.CrudRepository;
import hospital.entity.Wards;

import java.util.List;

public interface WardsRepository extends CrudRepository<Wards,Long> {

    List<Wards> findAllByName(String name);
}
