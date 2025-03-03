package RC.backend.Pacienti;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PacientiRepository extends JpaRepository<Pacienti, Integer> {

    List<Pacienti> findByStatus(Status status);
    List<Pacienti> findByDoctoriId(int doctoriId);


}