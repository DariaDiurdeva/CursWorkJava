package hospital.service;

import hospital.entity.Diagnosis;
import hospital.exception.DiagnosisNotFoundException;
import hospital.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public List<Diagnosis> allDiagnoses() {
       return  (List<Diagnosis>) diagnosisRepository.findAll();
    }

    @Override
    public Diagnosis findDiagnosisById(Long id) {
        return diagnosisRepository.findById(id).orElseThrow(()->new DiagnosisNotFoundException("Diagnosis not found"));
    }

    @Override
    public Diagnosis findDiagnosisByName(String name) {
        return  diagnosisRepository.findDiagnosisByName(name).orElseThrow(()->new DiagnosisNotFoundException("Diagnosis not found"));
    }

    @Override
    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
        if (diagnosisRepository.findDiagnosisByName(diagnosis.getName()).isEmpty()){
            return diagnosisRepository.save(diagnosis);
        } else {
            return diagnosisRepository.findDiagnosisByName(diagnosis.getName()).get();
        }

    }

    @Override
    public Diagnosis updateDiagnosis(Diagnosis diagnosis, Long id) {
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(id);
        if (optionalDiagnosis.isPresent()) {
           optionalDiagnosis.get().setName(diagnosis.getName());
            return diagnosisRepository.save(optionalDiagnosis.get());
        } else {
            throw new DiagnosisNotFoundException("Diagnosis not found");
        }
    }

    @Override
    public void deleteDiagnosis(Long id) {
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(id);
        if (optionalDiagnosis.isPresent()) {
            diagnosisRepository.deleteById(id);
        } else {
            throw new DiagnosisNotFoundException("Diagnose not found");
        }

    }

    @Override
    public void deleteAllDiagnoses() {
        diagnosisRepository.deleteAll();
    }
}
