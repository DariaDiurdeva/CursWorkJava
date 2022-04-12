package hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "diagnosis")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,  unique = true,updatable = false, insertable = false)
    private Long id;

    @Column(name = "name", length = 50, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "diagnosis_id", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<People> peoplesD;

    public Diagnosis(){
    }

    public Diagnosis(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return "Wards(id= " + id +
                ", name= " + name + ")";
    }

}
