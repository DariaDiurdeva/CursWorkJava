package hospital.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "wards")
public class Wards {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id", nullable = false,  unique = true, updatable = false, insertable = false)
      private Long id;

      @Column(name = "name", length = 50)
      private String name;

      @Column(name = "max_count")
      private int maxCount;

      @JsonIgnore
      @OneToMany(mappedBy = "wardId", cascade = CascadeType.ALL)
      private List<People> peoplesW;

      public Wards(){

      }

      public Wards(String name, int maxCount){
        this.name = name;
        this.maxCount = maxCount;
      }

      @Override
      public String toString(){
            return "Wards(id= " + id +
                    ", name= " + name +
                    ", max_count= "+ maxCount + ")";
      }
}
