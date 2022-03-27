package hospital.web;

import hospital.entity.Diagnosis;
import hospital.exception.DiagnosisNotFoundException;
import hospital.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/hospital")
public class DiagnosisController {

    private DiagnosisService diagnosisService;

    @Autowired
    public void setDiagnosisService(DiagnosisService diagnosisService){
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/diagnoses")
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis(){
        List<Diagnosis> list = diagnosisService.allDiagnoses();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/diagnosis/byId/{id}")
    public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(diagnosisService.findDiagnosisById(id));
        } catch (DiagnosisNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diagnose not found");
        }
    }

    @GetMapping("/diagnosis/byName/{name}")
    public ResponseEntity<Diagnosis> getDiagnosisByName(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(diagnosisService.findDiagnosisByName(name));
        } catch (DiagnosisNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diagnose not found");
        }
    }

    @DeleteMapping(value = "/deleteDiagnosis/{id}")
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable("id") Long id){
        try {
            diagnosisService.deleteDiagnosis(id);
            return ResponseEntity.ok().build();
        } catch (DiagnosisNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diagnosis not found");
        }
    }

    @DeleteMapping(value = "/deleteAllDiagnoses")
    public ResponseEntity<Void> deleteDiagnosis(){
        diagnosisService.deleteAllDiagnoses();
        return  ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addDiagnosis", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody Diagnosis newDiagnosis){
        return ResponseEntity.ok(diagnosisService.addDiagnosis(newDiagnosis));
    }

    @PutMapping(value = "/updateDiagnosis/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Diagnosis> updateDiagnosis(@RequestBody Diagnosis newInform, @PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(diagnosisService.updateDiagnosis(newInform,id));
        } catch (DiagnosisNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"diagnosis not found");
        }
    }
}
