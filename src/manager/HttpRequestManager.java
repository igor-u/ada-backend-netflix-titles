package manager;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestManager {

    public static String requestPorTitulo(String titulo) throws IOException, InterruptedException {

        String tituloCodificado = URLEncoder.encode(titulo);
        String url = "https://www.omdbapi.com/?t=" + tituloCodificado + "&apikey=3e13a2a8";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
