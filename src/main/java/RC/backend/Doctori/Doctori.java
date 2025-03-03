package RC.backend.Doctori;

import jakarta.persistence.*;
import lombok.Data;
import lombok.*;
@Entity
@Data
public class Doctori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String  specialization ;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Doctori manager;


}

