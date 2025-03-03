package RC.backend.Pacienti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
  // Definirea prefixului pentru rutele din controller
public class Controller {

    @Autowired
    private OpenAISearchService searchService;

    // Endpoint pentru test
    @GetMapping("/ping")
    public String ping() {
        return "Server is up and running!";
    }

        @PostMapping("/search")
        public ResponseEntity<String> search(@RequestParam String query) {
            String responseBody = searchService.getOpenAIResponse(query);
            return ResponseEntity.ok(responseBody);
        }
}
