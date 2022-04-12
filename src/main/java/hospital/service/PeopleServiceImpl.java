package hospital.service;

import hospital.entity.Diagnosis;
import hospital.entity.People;
import hospital.entity.PeopleInform;
import hospital.entity.Wards;
import hospital.exception.*;
import hospital.repository.DiagnosisRepository;
import hospital.repository.PeopleRepository;
import hospital.repository.WardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private WardsRepository wardsRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public People findPeople(Long id) {
        return peopleRepository.findById(id).orElseThrow(()->new PeopleNotFoundException("people not found"));
    }

    @Override
    public List<People> findAllPeoples() {
        return  (List<People>) peopleRepository.findAll();
    }

    @Override
    public People addPeople(PeopleInform peopleInform) {
        Optional<Wards> ward = wardsRepository.findById(peopleInform.getWard_id());
        Optional<Diagnosis> diagnosis = diagnosisRepository.findById(peopleInform.getDiagnosis_id());
        if(ward.isEmpty()){
            throw new WardsNotFoundException("Ward not found");
        }
        if (diagnosis.isEmpty()){
           throw new DiagnosisNotFoundException("Diagnosis not found");
        }
        if (ward.get().getMaxCount() > peopleRepository.countPeopleByWard_id(ward.get())){
            People people = new People(peopleInform.getFirst_name(),peopleInform.getLast_name(),
                    peopleInform.getFather_name(),ward.get(),diagnosis.get());
            return  peopleRepository.save(people);
        } else {
            throw new WardIsFullException("Ward is full, please change ward");
        }
    }

    @Override
    public Long countPeoplesByDiagnosis(String name) {
        return peopleRepository.countPeopleByDiagnosisName(name);
    }

    @Override
    public List<String> getWardByPeopleId(Long id) {
        return peopleRepository.getWardsByPeopleId(id);
    }

    @Override
    public List<String> getDiagnosisByPeopleID(Long id) {
        return peopleRepository.getDiagnosisByPeopleId(id);
    }

    @Override
    public List<String> getPeoplesInWard(Long idW) {
        return peopleRepository.getAllPeopleInWard(idW);
    }

    @Override
    public void deletePeople(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if (optionalPeople.isPresent()) {
            optionalPeople.get().getDiagnosis_id().getPeoplesD().removeIf(a-> Objects.equals(a.getId(), id));
            optionalPeople.get().getWard_id().getPeoplesW().removeIf(a->Objects.equals(a.getId(), id));
            peopleRepository.deleteById(id);
        } else {
            throw new PeopleNotFoundException("People not found");
        }
    }
    @Override
    public People updatePeople(PeopleInform people, Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        Optional<Wards> ward = wardsRepository.findById(people.getWard_id());
        Optional<Diagnosis> diagnosis = diagnosisRepository.findById(people.getDiagnosis_id());
        if (optionalPeople.isPresent()&& ward.isPresent() && diagnosis.isPresent()) {
           optionalPeople.get().setFirst_name(people.getFirst_name());
           optionalPeople.get().setLast_name(people.getLast_name());
           optionalPeople.get().setFather_name(people.getFather_name());
           optionalPeople.get().setWard_id(ward.get());
           optionalPeople.get().setDiagnosis_id(diagnosis.get());
            return peopleRepository.save(optionalPeople.get());
        } else {
            throw new PeopleNotFoundException("failed to update");
        }
    }

    @Override
    public void deleteAllPeoples() {
        peopleRepository.deleteAll();
    }

}
