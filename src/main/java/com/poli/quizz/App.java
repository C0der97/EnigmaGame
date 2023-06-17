package com.poli.quizz;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

/**
 * JavaFX App
 */
public class App extends Application {

    Utils utilidades = Utils.getInstance();

    @Override
    public void start(Stage stage) throws InterruptedException {
            stage.setResizable(false);
            StackPane loadingRoot = new StackPane();
            final MFXProgressSpinner sp = new MFXProgressSpinner();
            Label txt = new Label("Cargando...");
            txt.setTextFill(Color.WHITE);
            loadingRoot.getChildren().add(sp);
            loadingRoot.getChildren().add(txt);
            Scene indicador = new Scene(loadingRoot);
            MFXThemeManager.addOn(indicador, Themes.DEFAULT, Themes.LEGACY);
    
            stage.setTitle("Enigma");
            loadingRoot.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
            stage.setScene(indicador);
            stage.show();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(() -> {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    try {
                        cargarEscenaPrincipal(stage);
                    } catch (IOException e) {
                        System.out.println("Ha Ocurrido un error " + e.getMessage());
                    }
                });
            });

            executorService.shutdown();

    }

    private void cargarEscenaPrincipal(Stage stage) throws IOException {
        FXMLLoader loader = utilidades.getFxmlLoader("/fxml/User.fxml");
        Scene escena = Utils.createScene(loader);
        AppController controllerInitial = (AppController) Utils.getController(loader);
        controllerInitial.setStage(stage);
        controllerInitial.setScene(escena);
        stage.setScene(escena);
        stage.show();
        getSoundAndPlayAsync(controllerInitial);
    }

    private void getSoundAndPlayAsync(AppController controller) {
        if (StateManager.audioReproduce) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
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
