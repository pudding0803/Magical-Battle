package com.MagicalBattle.loaders;

import com.MagicalBattle.constants.Settings;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class ConfigLoader {
    private static final String FILE_PATH = Objects.requireNonNull(ConfigLoader.class.getResource(Settings.RESOURCE_PATH + "config/config.json")).getPath();

    public static long getAssetsCount() throws IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        reader.close();
        return (long) object.get("AssetCount");
    }

    public static void setAssetsCount(long value) throws IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        reader.close();
        object.put("AssetCount", value);
        FileWriter writer = new FileWriter(FILE_PATH);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(object.toString());
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
        Image image = ResourceLoader.getImage("images/background/" + object.get("BackgroundImage") + ".png");
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
        bufferedWriter.write(object.toString());
        bufferedWriter.close();
    }
}