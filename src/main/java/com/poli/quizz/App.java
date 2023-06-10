package com.poli.quizz;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            stage.setResizable(false);
            FXMLLoader loadr = new FXMLLoader(getClass().getResource("/fxml/User.fxml"));
            Parent load = loadr.load();
            Scene scene = new Scene(load);
            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
            AppController controllerInitial = (AppController) loadr.getController();
            getSoundAndPlayAsync(controllerInitial);
            controllerInitial.setStage(stage);
            controllerInitial.setScene(scene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Ha Ocurrido un error " + ex.getMessage());
        }

    }

    private void getSoundAndPlayAsync(AppController controller) {
        if (StateManager.audioReproduce) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                Utils u = new Utils();
                AudioInputStream audioInput = null;
                try {
                    audioInput = AudioSystem.getAudioInputStream(Utils.downloadUsingStream(
                            "https://rainhearth.000webhostapp.com/MoonKnight.wav"
                    ));
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Clip clip = null;
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
                try {
                    clip.open(audioInput);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                clip.start();
                controller.setClip(clip);
            });
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
