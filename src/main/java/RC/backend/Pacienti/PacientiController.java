package RC.backend.Pacienti;

import RC.backend.Doctori.DoctoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pacienti") // Ruta de bază corectă pentru Pacienti
@CrossOrigin(origins = "http://localhost:3000")
public class PacientiController {

    @Autowired
    private PacientiService pacientiService;
    @Autowired
    private DoctoriService doctoriService;

    @Autowired
    private SentEmailService sentEmailService;

    // POST pentru a adăuga un pacient
    @PostMapping
    public Pacienti addPacienti(@RequestBody Pacienti pacienti) {
        return pacientiService.createPacienti(pacienti);
    }

    // GET pentru a obține toți pacienții
    @GetMapping
    public List<Pacienti> getAllPacienti() {
        return pacientiService.getAllPacienti();
    }

    // PUT pentru a actualiza un pacient
    @PutMapping("/{id}")
    public Pacienti updatePacienti(@PathVariable Integer id, @RequestBody Pacienti pacienti) {
        return pacientiService.updatePacienti(id, pacienti);
    }

    // PATCH pentru a atribui un pacient unui doctor și a trimite o notificare
    @PatchMapping("/assign/{id}")
    public void assignPacienti(@PathVariable int id, @RequestBody Pacienti pacienti) {
        pacientiService.assignPacientToDoctorAndNotify(id, pacienti);
    }

    // PATCH pentru a actualiza statusul unui pacient
    @PatchMapping("/{id}")
    public Pacienti updatePacientiStatus(@PathVariable int id, @RequestBody Map<String, String> statusUpdate) {
        String statusString = statusUpdate.get("status");
        Status status = Status.valueOf(statusString.toUpperCase()); // Conversia din string în enum, cu majuscule
        return pacientiService.updatePacientiStatus(id, status);
    }

    // DELETE pentru a șterge un pacient
    @DeleteMapping("/{id}")
    public void deletePacienti(@PathVariable int id) {
        pacientiService.deletePacient(id);
    }

    // GET pentru a obține pacienții atribuiți unui doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Pacienti> getPacientiByDoctor(@PathVariable int doctorId) {
        return pacientiService.getPacientiByDoctori(doctorId);
    }

}
