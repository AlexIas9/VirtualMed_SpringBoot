package RC.backend.Doctori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctori")
@CrossOrigin(origins = "http://localhost:3000")

public class DoctoriController {
    @Autowired
    private DoctoriService doctoriService;

    // POST pentru a adăuga un curier
    @PostMapping
    public Doctori addDoctori(@RequestBody Doctori doctori) {
        return doctoriService.createDoctori(doctori);
    }

    // GET pentru a obține toți Doctori
    @GetMapping
    public List<Doctori> getAllDoctori() {
        return doctoriService.getAllDoctori();
    }

    // PUT pentru a actualiza un doctor
    @PutMapping("/{id}")
    public Doctori updateDoctori(@PathVariable int id, @RequestBody Doctori courier) {
        return doctoriService.updateDoctori(id, courier);
    }

    // DELETE pentru a șterge un curier
    @DeleteMapping("/{id}")
    public void deleteDoctori(@PathVariable int id) {
        doctoriService.deleteDoctori(id);
    }

}
