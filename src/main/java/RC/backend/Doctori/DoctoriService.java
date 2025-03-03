package RC.backend.Doctori;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctoriService {

    @Autowired
    private DoctoriRepository doctoriRepository;

    // Creare Doctor
    public Doctori createDoctori(Doctori doctori) {
        return doctoriRepository.save(doctori);
    }

    // Obține toți Doctorii
    public List<Doctori> getAllDoctori() {
        return doctoriRepository.findAll();
    }

    // Actualizează un curier
    public Doctori updateDoctori(Integer id, Doctori doctori) {
        Doctori existingDoctori = doctoriRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        if (doctori.getName() != null) {
            existingDoctori.setName(doctori.getName());
        }
        if (doctori.getEmail() != null) {
            existingDoctori.setEmail(doctori.getEmail());
        }
        if (doctori.getSpecialization() != null) {
            existingDoctori.setSpecialization(doctori.getSpecialization());
        }
        if (doctori.getStatus() != null) {
            existingDoctori.setStatus(doctori.getStatus());
        }
        if (doctori.getPassword() != null) {
            existingDoctori.setPassword(doctori.getPassword());
        }
        return doctoriRepository.save(existingDoctori);
    }

    // Șterge un curier
    public void deleteDoctori(Integer id) {
        if (!doctoriRepository.existsById(id)) {
            throw new IllegalArgumentException("Courier not found");
        }
        doctoriRepository.deleteById(id);
    }


    public Doctori getDoctoriById(Integer id) {
        return doctoriRepository.findById(id).orElse(null);
    }
}
