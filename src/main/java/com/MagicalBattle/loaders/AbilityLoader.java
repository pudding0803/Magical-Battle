package com.MagicalBattle.loaders;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.AbilitySet;
import com.MagicalBattle.models.enums.CharacterClass;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class AbilityLoader {
    private static final XSSFWorkbook book;

    static {
        try {
            book = new XSSFWorkbook(new FileInputStream(Objects.requireNonNull(AbilityLoader.class.getResource(Settings.RESOURCE_PATH + "config/Ability.xlsx")).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final XSSFSheet sheet = book.getSheetAt(0);

    private static final HashMap<CharacterClass, AbilitySet> abilityValues = new HashMap<>();
    private static final HashMap<CharacterClass, AbilitySet> abilityRatios = new HashMap<>();

    public static AbilitySet getAbilityValue(CharacterClass character) {
        return abilityValues.get(character);
    }

    public static AbilitySet getAbilityRatio(CharacterClass character) {
        return abilityRatios.get(character);
    }

    public static void loadAbilities() {
        for (int i = 0; i < 6; i++) {
            XSSFRow row = sheet.getRow(i * 2 + 1);
            double[] abilityArray = new double[6];
            for (int j = 0; j < 6; j++) {
                abilityArray[j] = row.getCell(j + 2).getNumericCellValue();
            }
            abilityValues.put(CharacterClass.getCharacter(i), new AbilitySet(abilityArray));
        }
        for (int i = 0; i < 6; i++) {
            XSSFRow row = sheet.getRow(i * 2 + 2);
            double[] abilityArray = new double[6];
            for (int j = 0; j < 6; j++) {
                abilityArray[j] = row.getCell(j + 2).getNumericCellValue();
            }
            abilityRatios.put(CharacterClass.getCharacter(i), new AbilitySet(abilityArray));
        }
    }
}