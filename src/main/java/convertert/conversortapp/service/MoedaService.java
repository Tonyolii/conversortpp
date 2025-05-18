package convertert.conversortapp.service;

import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class MoedaService {

    public static double converterMoeda(String de, String para, double valor) throws Exception {
        String url = "https://api.exchangerate-api.com/v4/latest/" + de.toUpperCase();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());
        double taxaPara = json.getJSONObject("rates").getDouble(para.toUpperCase());

        return valor * taxaPara;
    }
}
