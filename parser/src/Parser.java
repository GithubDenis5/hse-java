import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Parser {

    // обращение и парсинг сайта в строку
    private String getDataFromWeb(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка полуения данных с сайта ");
        }
        return response.toString();
    }

    //  создание json из строки и вывод
    private void printJson(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println("\nobject #" + (i+1));
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                printJsonObj(jsonObject);
            }
        } catch (Exception e) {
            System.err.println("Ошибка чтения и вывода json");
        }
    }

    // вывод объекта из json
    private void printJsonObj(JSONObject jsonObj) {
        for (String key : jsonObj.keySet()) {
            Object value = jsonObj.get(key);
            System.out.println(key + ": " + value);
        }
    }

    public void parseAndPrint(String url) {
        System.out.println("Парсим страницу: " + url);
        String parsedJson = getDataFromWeb(url);
        if (!parsedJson.isEmpty()) {
            System.out.println("Выводим объекты...\n");
            printJson(parsedJson);
        }
    }
}
