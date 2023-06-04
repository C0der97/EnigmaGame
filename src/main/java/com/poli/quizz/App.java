package com.poli.quizz;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * JavaFX App
 */
public class App extends Application {


    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader loadr = new FXMLLoader(getClass().getResource("/fxml/App.fxml"));
            Parent load = loadr.load();
            var scene = new Scene(load);
            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
            var controllerInitial = (AppController) loadr.getController();
            controllerInitial.setStage(stage);
            stage.setScene(scene);
            stage.show();
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource("/audio/MoonKnight.wav").getPath()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            controllerInitial.setClip(clip);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.println("Ha Ocurrido un error");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
