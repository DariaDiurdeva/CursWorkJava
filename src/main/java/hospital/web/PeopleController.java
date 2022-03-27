package hospital.web;

import hospital.entity.People;
import hospital.entity.PeopleInform;
import hospital.exception.DiagnosisNotFoundException;
import hospital.exception.PeopleNotFoundException;
import hospital.exception.WardIsFullException;
import hospital.exception.WardsNotFoundException;
import hospital.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/hospital")
public class PeopleController {
    private PeopleService peopleService;

    @Autowired
    public void setPeopleService(PeopleService peopleService){
        this.peopleService = peopleService;
    }

    @GetMapping("/peoples")
    public ResponseEntity<List<People>> getAllPeople(){
        List<People> list = peopleService.findAllPeoples();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<People> getPeople(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(peopleService.findPeople(id));
        } catch (PeopleNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "People not found");
        }
    }


    @GetMapping("/countPeoplesByDiagnosis/{name}")
    public ResponseEntity<Long> countPeoplesByDiagnosis(@PathVariable("name") String name){
        try{
            return ResponseEntity.ok(peopleService.countPeoplesByDiagnosis(name));
        } catch (DiagnosisNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diagnosis not found");
        }
    }


    @GetMapping("/wardByPeopleId/{id}")
    public ResponseEntity<List<String>> getWardByPeopleId(@PathVariable("id") Long id){
        try {
            List<String > list = peopleService.getWardByPeopleId(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (PeopleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "People not found");
        }
    }

    @GetMapping("/diagnosisByPeopleId/{id}")
    public ResponseEntity<List<String >> getPeoplesByDiagnosis(@PathVariable("id") Long id){
        try {
            List<String> list = peopleService.getDiagnosisByPeopleID(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (PeopleNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "people not found");
        }
    }

    @GetMapping("/peoplesInWard/{idW}")
    public ResponseEntity<List<People>> getPeopleInWard(@PathVariable("idW") Long idW){
        try {
            return ResponseEntity.ok(peopleService.getPeoplesInWard(idW));
        } catch (RuntimeException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wards not found");
        }
    }

    @DeleteMapping(value = "/deleteAllPeoples")
    public ResponseEntity<Void> deletePeoples(){
        peopleService.deleteAllPeoples();
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deletePeople/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable("id") Long id){
        try {
            peopleService.deletePeople(id);
            return ResponseEntity.ok().build();
        } catch (PeopleNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "People not found");
        }
    }

    @PostMapping(value = "/addPeople", consumes = "application/json", produces = "application/json")
    public ResponseEntity<People> addPeople(@RequestBody PeopleInform peopleInform) {
        try {
            return ResponseEntity.ok(peopleService.addPeople(peopleInform));
        } catch(WardsNotFoundException | DiagnosisNotFoundException | WardIsFullException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add person");
        }
    }

    @PutMapping(value = "/updatePeople/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<People> updatePeople(@RequestBody PeopleInform newInform, @PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(peopleService.updatePeople(newInform,id));
        } catch (PeopleNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"People not found");
        }
    }

}
