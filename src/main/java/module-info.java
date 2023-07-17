module com.example.magicalbattle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires json.simple;
    requires org.apache.poi.ooxml;

    exports com.MagicalBattle;
    exports com.MagicalBattle.controllers;
    exports com.MagicalBattle.models;
    exports com.MagicalBattle.models.enums;
    exports com.MagicalBattle.models.Character;
    exports com.MagicalBattle.models.SkillObject;
    exports com.MagicalBattle.models.DisplayObject;
    exports com.MagicalBattle.models.DisplayObject.EffectObject;
    exports com.MagicalBattle.models.DisplayObject.LabelObject;
    exports com.MagicalBattle.models.Status;
    exports com.MagicalBattle.loaders;

    opens com.MagicalBattle to javafx.fxml;
    opens com.MagicalBattle.config to javafx.fxml;
    opens com.MagicalBattle.controllers to javafx.fxml;
    opens com.MagicalBattle.loaders to javafx.fxml;
}