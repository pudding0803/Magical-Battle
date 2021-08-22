package MagicalBattle.config;

import java.io.*;
import java.util.Objects;

import MagicalBattle.controllers.BackgroundController;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigProcessor {
    private static final String FILE_PATH = "src/MagicalBattle/config/config.json";

    public static long getAssetsNumber() throws IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        reader.close();
        return (long) object.get("AssetsNumber");
    }

    public static void setAssetsNumber(int value) throws IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        reader.close();
        object.put("AssetsNumber", value);
        FileWriter writer = new FileWriter(FILE_PATH);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(object.toJSONString());
        bufferedWriter.close();
    }

    public static String getBackgroundName() throws IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        reader.close();
        return (String) object.get("BackgroundImage");
    }

    public static Background getBackgroundImage() throws IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        reader.close();
        Image image = new Image(Objects.requireNonNull(
                BackgroundController.class.getResource("../assets/background/" + object.get("BackgroundImage") + ".png")
        ).toExternalForm());
        return new Background(new BackgroundImage(image, null, null, null, null));
    }

    public static void setBackgroundImage(String fileName) throws IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        reader.close();
        object.put("BackgroundImage", fileName);
        FileWriter writer = new FileWriter(FILE_PATH);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(object.toJSONString());
        bufferedWriter.close();
    }
}