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
            //stream = App.class.getResourceAsStream("test/test.fxml");
            //var cosa = getClass().getResource("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Quizz\\src\\main\\java\\com\\poli\\quizz\\App.fxml");
            FXMLLoader loadr = new FXMLLoader(getClass().getResource("/fxml/App.fxml"));

            //BufferedInputStream buffer = new BufferedInputStream(
            //new FileInputStream(new File("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Quizz\\src\\main\\resources\\audio\\lofi.mp3")));
            Parent load = loadr.load();
            /// var scene = new Scene(new StackPane(btn), 480, 640);
            var scene = new Scene(load);
            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
            stage.setScene(scene);
            stage.show();
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource("/audio/lofi.wav").getPath()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.println("Debe ingresar obligatoriamente un número entero.");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
