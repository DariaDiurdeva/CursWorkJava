package hospital.entity;

import lombok.Getter;

@Getter
public class PeopleInform {
   private String first_name;
   private String last_name;
   private String father_name;
   private Long ward_id;
   private Long diagnosis_id;

    public PeopleInform(String first_name, String last_name, String father_name, Long wardId, Long diagnosisId){
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.ward_id = wardId;
        this.diagnosis_id=diagnosisId;
    }
}
