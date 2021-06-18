package MagicalBattle.config;

import java.io.*;

public class AssetsNumProcessor {
    private static final String filePath = "src/MagicalBattle/config/AssetsNum.txt";

    public static int getAssetsNum() throws IOException {
        FileReader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        int count = Integer.parseInt(bufferedReader.readLine());
        reader.close();
        return count;
    }

    public static void setAssetsNum(int value) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(Integer.toString(value));
        bufferedWriter.close();
    }
}