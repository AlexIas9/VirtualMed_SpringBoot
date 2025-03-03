package RC.backend.Pacienti;

import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OpenAISearchService {

    private static final String API_KEY = "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"; // Înlocuiește cu cheia ta
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions"; // URL pentru OpenAI API

    public String getOpenAIResponse(String query) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // Creează corpul cererii
            String requestBody = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}],\"max_tokens\":1024}", query);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OPENAI_API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Analizează răspunsul JSON
            JSONObject jsonResponse = new JSONObject(response.body());
            String assistantResponse = jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content"); // Extrage doar conținutul răspunsului

            return assistantResponse; // Returnează doar textul din câmpul 'content'
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while making the OpenAI request.";
        }
    }
}
