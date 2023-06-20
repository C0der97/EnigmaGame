package com.poli.quizz;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

    Utils utilidades = Utils.getInstance();

    @Override
    public void start(Stage stage) throws InterruptedException {
            stage.setResizable(false);
            Utils.mostrarLoader(stage);

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        try {
                            stage.close();
                            cargarEscenaPrincipal(stage);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });

            executorService.shutdown();

    }

    private void cargarEscenaPrincipal(Stage stage) throws IOException {
        FXMLLoader loader = utilidades.getFxmlLoader("/fxml/User.fxml");
        Scene escena = Utils.createScene(loader);
        AppController controllerInitial = (AppController) Utils.getController(loader);
        stage.getIcons().add(new Image("icono.jpg"));
        controllerInitial.setStage(stage);
        controllerInitial.setScene(escena);
        //getSoundAndPlayAsync(controllerInitial);
        stage.setScene(escena);
        stage.show();
    }

    private void getSoundAndPlayAsync(AppController controller) {
        if (StateManager.audioReproduce) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                AudioInputStream audioInput = null;
                try {
                    audioInput = AudioSystem.getAudioInputStream(Utils.downloadUsingStream(
                            "https://rainhearth.000webhostapp.com/gameintro.wav"
                    ));
                } catch (UnsupportedAudioFileException | IOException e) {
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
                } catch (LineUnavailableException | IOException e) {
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
