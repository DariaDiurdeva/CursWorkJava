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
        Optional<Wards> ward = wardsRepository.findById(peopleInform.getWardId());
        Optional<Diagnosis> diagnosis = diagnosisRepository.findById(peopleInform.getDiagnosisId());
        if(ward.isEmpty()){
            throw new WardsNotFoundException("Ward not found");
        }
        if (diagnosis.isEmpty()){
           throw new DiagnosisNotFoundException("Diagnosis not found");
        }
        if (ward.get().getMaxCount() > peopleRepository.countPeopleByWardId(ward.get())){
            People people = new People(peopleInform.getFirst_name(),peopleInform.getLast_name(),
                    peopleInform.getFather_name(),ward.get(),diagnosis.get());

            return  peopleRepository.save(people);
        } else {
            throw new WardIsFullException("Ward is full, please change ward");
        }
    }

    @Override
    public Long countPeoplesByDiagnosis(String name) {
        Optional<Diagnosis> diagnosis = diagnosisRepository.findDiagnosisByName(name);
        if(diagnosis.isPresent()){
            return peopleRepository.countPeopleByDiagnosisId(diagnosis.get());
        }else {
            throw new DiagnosisNotFoundException("Diagnosis not found");
        }
    }

    @Override
    public List<String> getWardByPeopleId(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if (optionalPeople.isPresent()) {
            return peopleRepository.getWardsByPeopleId(id);
        } else {
            throw new PeopleNotFoundException("People not found");
        }
    }

    @Override
    public List<String> getDiagnosisByPeopleID(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if (optionalPeople.isPresent()) {
            return peopleRepository.getDiagnosisByPeopleId(id);
        } else {
            throw new PeopleNotFoundException("People not found");
        }
    }

    @Override
    public List<People> getPeoplesInWard(Long idW) {
        Optional<Wards> ward = wardsRepository.findById(idW);
        if (ward.isPresent()){
            return peopleRepository.getAllPeopleInWard(idW);
        } else {
            throw new RuntimeException("Ward  not found");
        }

    }

    @Override
    public void deletePeople(Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        if (optionalPeople.isPresent()) {
            peopleRepository.deleteById(id);
        } else {
            throw new PeopleNotFoundException("People not found");
        }
    }
    @Override
    public People updatePeople(PeopleInform people, Long id) {
        Optional<People> optionalPeople = peopleRepository.findById(id);
        Optional<Wards> ward = wardsRepository.findById(people.getWardId());
        Optional<Diagnosis> diagnosis = diagnosisRepository.findById(people.getDiagnosisId());
        if (optionalPeople.isPresent() && ward.isPresent() && diagnosis.isPresent()) {
           optionalPeople.get().setFirst_name(people.getFirst_name());
           optionalPeople.get().setLast_name(people.getLast_name());
           optionalPeople.get().setFather_name(people.getFather_name());
           optionalPeople.get().setWardId(ward.get());
           optionalPeople.get().setDiagnosisId(diagnosis.get());
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
