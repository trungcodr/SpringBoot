package bai3;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

@SpringBootApplication
public class Bai3Application implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Hello World");
        try {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(new FileReader("User_Profile.json"));

            if (element.isJsonArray()) {
                JsonArray array = element.getAsJsonArray();
                for (JsonElement userElement : array) {
                    JsonObject userObj = userElement.getAsJsonObject();

                    String userId = userObj.get("userId").getAsString();
                    JsonObject profileData = userObj.get("profileData").getAsJsonObject();

                    System.out.println("User ID: " + userId);
                    for (Map.Entry<String, JsonElement> entry : profileData.entrySet()) {
                        String key = entry.getKey();
                        JsonElement value = entry.getValue();
                        System.out.println(" - " + key + ": " + value);
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
//    Dem xem co bao nhieu nguoi dung co thong tin ve thanh pho - residence
        int count = 0;
        try {
            JsonElement root = JsonParser.parseReader(new FileReader("User_Profile.json"));
            if (root.isJsonArray()) {
                JsonArray users = root.getAsJsonArray();

                for (JsonElement userElement : users) {
                    JsonObject userObj = userElement.getAsJsonObject();
                    JsonObject profileData = userObj.get("profileData").getAsJsonObject();

                    if (profileData.has("residence")) {
                        count++;
                    }
                }
                System.out.println("So nguoi co thong tin ve thanh pho la: " + count);
            }
        } catch (Exception e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    public static void main(String[] args) { SpringApplication.run(Bai3Application.class, args);}


}
