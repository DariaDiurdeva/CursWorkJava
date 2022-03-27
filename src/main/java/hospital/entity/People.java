package hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;


import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,  unique = true,updatable = false, insertable = false)
    private Long id;

    @Column(name = "first_name", length = 20)
    private String first_name;

    @Column(name = "last_name", length = 20)
    private String last_name;

    @Column(name = "father_name", length = 20)
    private String father_name;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Wards wardId;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosisId;

    public People(){

    }

    public People(String first_name, String last_name, String father_name, Wards ward_id, Diagnosis diagnosis_id){
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.diagnosisId = diagnosis_id;
        this.wardId = ward_id;
    }

    @Override
    public String toString(){
        return  "Peoples (id= "+ id
                + ", first_name= " + first_name
                + ", last_name= " + last_name
                + ", father_name= "+ first_name
                + ", ward_id= " + wardId.getId()
                + ", diagnosis_id= " + diagnosisId.getId() + ")";
    }

}
