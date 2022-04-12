package hospital.service;

import hospital.entity.Wards;
import hospital.exception.WardsNotFoundException;
import hospital.repository.WardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WardsServiceImpl implements WardsService{

    @Autowired
    private WardsRepository wardsRepository;

    @Override
    public List<Wards> allWards() {
        return  (List<Wards>) wardsRepository.findAll();
    }


    @Override
    public Wards findWardById(Long id) {
        return wardsRepository.findById(id).orElseThrow(()->new WardsNotFoundException("wards not found"));
    }

    @Override
    public List<Wards> findWardsByName(String name) {
        return  wardsRepository.findAllByName(name);
    }

    @Override
    public Wards addWard(Wards ward) {
        return wardsRepository.save(ward);
    }

    @Override
    public Wards updateWard(Wards ward, Long id) {
        Optional<Wards> optionalWards = wardsRepository.findById(id);
        if (optionalWards.isPresent()) {
            optionalWards.get().setName(ward.getName());
            optionalWards.get().setMaxCount(ward.getMaxCount());
           return wardsRepository.save(optionalWards.get());
        } else {
            throw new WardsNotFoundException("ward not found");
        }
    }

    @Override
    public void deleteWard(Long id) {
        /*Optional<Wards> optionalWards = wardsRepository.findById(id);
        if (optionalWards.isPresent()) {*/
            wardsRepository.deleteById(id);
       /* } else {
            throw new WardsNotFoundException("Ward not found");
        }*/
    }

    @Override
    public void deleteALlWards() {
        wardsRepository.deleteAll();
    }
}
