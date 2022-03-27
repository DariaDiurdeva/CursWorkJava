package hospital.service;

import hospital.entity.People;
import hospital.entity.PeopleInform;
import java.util.List;

public interface PeopleService  {

    People findPeople(Long id);
    List<People> findAllPeoples();

    Long countPeoplesByDiagnosis(String name);

    List<String> getWardByPeopleId(Long id);
    List<String> getDiagnosisByPeopleID(Long id);
    List<People> getPeoplesInWard(Long idW);

    People addPeople(PeopleInform peopleInform);
    People updatePeople(PeopleInform people, Long id);
    void deletePeople(Long id);
    void deleteAllPeoples();
}
