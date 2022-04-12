package hospital.service;

import hospital.entity.Wards;
import java.util.List;

public interface WardsService{
    List<Wards> allWards();
    Wards findWardById(Long id);
    List<Wards> findWardsByName(String name);
    Wards addWard(Wards ward);
    Wards updateWard(Wards ward, Long id);
    void deleteWard(Long id);
    void deleteALlWards();
}
