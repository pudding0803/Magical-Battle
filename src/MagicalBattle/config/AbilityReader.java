package MagicalBattle.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import MagicalBattle.models.enums.Career;
import MagicalBattle.models.AbilityValue;

public class AbilityReader {
    private static XSSFWorkbook book = new XSSFWorkbook();

    static {
        try {
            FileInputStream input = new FileInputStream("src/MagicalBattle/config/Ability.xlsx");
            book = new XSSFWorkbook(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final XSSFSheet sheet = book.getSheetAt(0);

    public static AbilityValue getAbilityValue(Career career) {
        XSSFRow row = sheet.getRow(career.ordinal() * 2 - 1);
        return new AbilityValue(
                row.getCell(2).getNumericCellValue(),
                row.getCell(3).getNumericCellValue(),
                row.getCell(4).getNumericCellValue(),
                row.getCell(5).getNumericCellValue(),
                row.getCell(6).getNumericCellValue(),
                row.getCell(7).getNumericCellValue()
        );
    }

    public static AbilityValue getAbilityRate(Career career) {
        XSSFRow row = sheet.getRow(career.ordinal() * 2);
        return new AbilityValue(
                row.getCell(2).getNumericCellValue(),
                row.getCell(3).getNumericCellValue(),
                row.getCell(4).getNumericCellValue(),
                row.getCell(5).getNumericCellValue(),
                row.getCell(6).getNumericCellValue(),
                row.getCell(7).getNumericCellValue()
        );
    }
}