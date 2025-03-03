package RC.backend.Pacienti;

import RC.backend.Doctori.Doctori;
import RC.backend.Doctori.DoctoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientiService {

    @Autowired
    private DoctoriService doctoriService;

    @Autowired
    private PacientiRepository pacientiRepository;

    @Autowired
    private SentEmailService sentEmailService;

    public Pacienti createPacienti(Pacienti pacienti) {
        return pacientiRepository.save(pacienti);
    }

    public List<Pacienti> getAllPacienti() {
        return pacientiRepository.findAll();
    }

    public Pacienti updatePacienti(Integer id, Pacienti pacient) {
        Pacienti existingPacient = pacientiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pacient not found"));

        if (pacient.getName() != null) {
            existingPacient.setName(pacient.getName());
        }
        if (pacient.getEmail() != null) {
            existingPacient.setEmail(pacient.getEmail());
        }
        if (pacient.getPassword() != null) {
            existingPacient.setPassword(pacient.getPassword());
        }
        if (pacient.getDoctori() != null) {
            existingPacient.setDoctori(pacient.getDoctori());
        }
        if (pacient.getStatus() != null) {
            existingPacient.setStatus(pacient.getStatus());
        }

        return pacientiRepository.save(existingPacient);
    }

    public List<Pacienti> getPacientiByStatus(String status) {
        Status statusEnum = Status.valueOf(status.toUpperCase());
        return pacientiRepository.findByStatus(statusEnum);
    }

    public void deletePacient(Integer id) {
        if (!pacientiRepository.existsById(id)) {
            throw new IllegalArgumentException("Pacient not found");
        }
        pacientiRepository.deleteById(id);
    }

    public Pacienti assignPacientToDoctor(Integer id, Pacienti pacienti) {
        Pacienti existingPacient = pacientiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pacient not found"));

        if (pacienti.getDoctori() != null) {
            existingPacient.setDoctori(pacienti.getDoctori());
            existingPacient.setStatus(pacienti.getStatus() != null ? pacienti.getStatus() : existingPacient.getStatus());
        }

        return pacientiRepository.save(existingPacient);
    }

    public Pacienti assignPacientToDoctorAndNotify(Integer id, Pacienti pacienti) {
        Pacienti assignedPacient = this.assignPacientToDoctor(id, pacienti);

        if (assignedPacient.getDoctori() == null) {
            System.out.println("Eroare: Pacientul nu a fost atribuit niciunui doctor.");
            return assignedPacient;
        }

        Integer doctorId = assignedPacient.getDoctori().getId();
        System.out.println("ID-ul doctorului: " + doctorId);

        Doctori doctori = doctoriService.getDoctoriById(doctorId);
        if (doctori == null) {
            System.out.println("Eroare: Doctorul nu a fost găsit.");
            return assignedPacient;
        }

        if (doctori.getEmail() != null && !doctori.getEmail().isEmpty()) {
            String emailBody = "Bună ziua, " + doctori.getName() + "!\n\n" +
                    "Un nou pacient ți-a fost atribuit. Te rugăm să verifici detaliile în sistem.\n\n" +
                    "Cu respect,\nEchipa Medicală";

            String emailSubject = "Pacient nou atribuit";

            sentEmailService.sendPacientiAssignedEmail(doctori.getEmail(), emailBody, emailSubject);
        } else {
            System.out.println("Eroare: Doctorul nu are un email valid.");
        }

        return assignedPacient;
    }

    public Pacienti updatePacientiStatus(Integer id, Status status) {
        Pacienti existingPacienti = pacientiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pacienti not found"));

        existingPacienti.setStatus(status);
        return pacientiRepository.save(existingPacienti);
    }

    public List<Pacienti> getPacientiByDoctori(int doctoriId) {
        return pacientiRepository.findByDoctoriId(doctoriId);
    }
}
