module com.example.magicalbattle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires json.simple;
    requires org.apache.poi.ooxml;

    exports com.MagicalBattle;
    exports com.MagicalBattle.controllers;
    exports com.MagicalBattle.models;
    exports com.MagicalBattle.models.Enums;
    exports com.MagicalBattle.models.Character;
    exports com.MagicalBattle.models.SkillObject;
    exports com.MagicalBattle.models.EffectObject;
    exports com.MagicalBattle.loaders;

    opens com.MagicalBattle to javafx.fxml;
    opens com.MagicalBattle.config to javafx.fxml;
    opens com.MagicalBattle.controllers to javafx.fxml;
    opens com.MagicalBattle.loaders to javafx.fxml;
}