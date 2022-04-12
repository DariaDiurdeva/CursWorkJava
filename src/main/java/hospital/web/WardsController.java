package hospital.web;

import hospital.entity.People;
import hospital.entity.Wards;
import hospital.exception.WardsNotFoundException;
import hospital.service.WardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/hospital")
public class WardsController {
    private WardsService wardsService;

    @Autowired
    public void setWardsService(WardsService wardsService){
        this.wardsService = wardsService;
    }

    @GetMapping("/wards")
    public ResponseEntity<List<Wards>> getAllWards(){
        List<Wards> list = wardsService.allWards();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/ward/byId/{id}")
    public ResponseEntity<Wards> getWardsById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(wardsService.findWardById(id));
        } catch (WardsNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ward not found");
        }
    }

    @GetMapping("/ward/byName/{name}")
    public ResponseEntity<List<Wards>> getWardsByName(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(wardsService.findWardsByName(name));
        } catch (WardsNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ward not found");
        }
    }

    @DeleteMapping(value = "/deleteWard/{id}")
    public ResponseEntity<Void> deleteWards(@PathVariable("id") Long id){
        try {
            wardsService.deleteWard(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ward not found");
        }
    }

    @PostMapping(value = "/addWard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Wards> addWards(@RequestBody Wards ward){
        return ResponseEntity.ok(wardsService.addWard(ward));
    }

    @PutMapping(value = "/updateWard/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Wards> updateWards(@RequestBody Wards newWard, @PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(wardsService.updateWard(newWard,id));
        } catch (WardsNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"wards not found");
        }
    }

    @DeleteMapping(value = "/deleteWards")
    public ResponseEntity<Void> deleteWards() {
        wardsService.deleteALlWards();
        return ResponseEntity.ok().build();
    }
}
