package hospital.service;

import hospital.entity.Diagnosis;
import java.util.List;

public interface DiagnosisService {
    List<Diagnosis> allDiagnoses();
    Diagnosis findDiagnosisById(Long id);
    Diagnosis findDiagnosisByName(String name);
    Diagnosis addDiagnosis(Diagnosis diagnosis);
    Diagnosis updateDiagnosis(Diagnosis diagnosis, Long id);
    void deleteDiagnosis(Long id);
    void deleteAllDiagnoses();
}
