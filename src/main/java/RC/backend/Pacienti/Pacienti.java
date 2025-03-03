package RC.backend.Pacienti;

import RC.backend.Doctori.Doctori;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
public class Pacienti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createdOn;

    private String name;
    private String email;
    private String password;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "doctori_id")
    private Doctori doctori;
}
