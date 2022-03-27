/*package hospital;

import hospital.entity.Diagnosis;
import hospital.entity.People;
import hospital.entity.PeopleInform;
import hospital.entity.Wards;
import hospital.exception.DiagnosisNotFoundException;
import hospital.exception.PeopleNotFoundException;
import hospital.exception.WardsNotFoundException;
import hospital.service.DiagnosisService;
import hospital.service.PeopleService;
import hospital.service.WardsService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class Request {

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private WardsService wardsService;

    @Autowired
    private PeopleService peopleService;

    public void RequestWards(){
        wardsService.addWards(new Wards("Double",2));
        wardsService.addWards(new Wards("Lux",1));
        wardsService.addWards(new Wards("Ordinary",4));

        List<Wards> allWards =  new ArrayList<>(wardsService.allWards());
        for(Wards value : allWards){
            System.out.println(value.toString());
        }

        try{
            Wards ward = wardsService.findWardsById(1L);
            System.out.println(ward.toString());

            Wards ward2 = wardsService.findWardsByName("Lux");
            System.out.println(ward2.toString());

            Wards updateWard = wardsService.updateWards(new Wards("Ordinary",6),4L);
            System.out.println(updateWard.toString());

            int maxCountById = wardsService.getMaxCount(2L);
            System.out.printf("Max count Lux ward = %d", maxCountById);

            wardsService.deleteWards(3L);
            System.out.printf("После удаления одной палаты общее число палат = %d", wardsService.count());
        } catch (WardsNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void RequestDiagnosis(){
        diagnosisService.addDiagnosis(new Diagnosis("Covid-19"));
        diagnosisService.addDiagnosis(new Diagnosis("Pneumonia"));
        diagnosisService.addDiagnosis(new Diagnosis("Heart attack"));

        List<Diagnosis> allDiagnosis =  new ArrayList<>(diagnosisService.allDiagnosis());
        for(Diagnosis value : allDiagnosis){
            System.out.println(value.toString());
        }

        try{
            Diagnosis diagnosis = diagnosisService.findDiagnosisById(1L);
            System.out.println(diagnosis.toString());

            Diagnosis diagnosis1 = diagnosisService.findDiagnosisByName("COVID-19");
            System.out.println(diagnosis1.toString());

            Diagnosis  updateDiagnosis = diagnosisService.updateDiagnosis(new Diagnosis("COVID-20"),1L);
            System.out.println(updateDiagnosis.toString());

            diagnosisService.deleteDiagnosis(1L);
            System.out.printf("После удаления одного из диагнозов общее число диагнозов = %d", diagnosisService.count());
        } catch (DiagnosisNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void RequestPeople(){
        try {
            peopleService.addPeople(new PeopleInform("Dasha", "Diurdeva", "Sergeevna", 1L, 1L));
            peopleService.addPeople(new PeopleInform("Masha", "Myshkina", "Alexsandrovna", 2L, 2L));
            peopleService.addPeople(new PeopleInform("Pavel", "Pentov", "Petrovich", 1L, 3L));

            List<People> allPeople = new ArrayList<>(peopleService.listPeople());
            for (People value : allPeople) {
                System.out.println(value.toString());
            }

            System.out.println("Люди из второй палаты");
            List<People> allPeopleInWards = wardsService.getAllPeopleByWardsId(2L);
            for (People value : allPeopleInWards) {
                System.out.println(value.toString());
            }

            peopleService.updatePeople(new PeopleInform("Masha", "Myshkina", "Alexsandrovna",
                    3L, 2L), 2L);

            System.out.printf("После обновления данных во второй палате осталось %d человек\n",
                    wardsService.getAllPeopleByWardsId(2L).size());


            System.out.printf("Людей с диагнозом 3 -  %d", diagnosisService.getAllPeoplesByDiagnosisId(3L).size());
            peopleService.deletePeople(3L);
            System.out.printf("После удаления человека с диагнозом 3, осталось людей с этим диагнозом - %d\n",
                    diagnosisService.getAllPeoplesByDiagnosisId(3L).size());

            List<People> allPeople2 = new ArrayList<>(peopleService.listPeople());
            for (People value : allPeople2) {
                System.out.println(value.toString());
            }
            wardsService.deleteWards(1L);
            diagnosisService.deleteDiagnosis(2L);

            System.out.printf("После удаления 1 палаты и 2 диагноза осталось %d людей\n", peopleService.listPeople().size());
        } catch (PeopleNotFoundException | WardsNotFoundException | DiagnosisNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}*/
