/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta1;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import models.PreguntaMultiple;

/**
 *
 * @author bare-
 */
public final class Utils {

    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public static Scene scene;

    static boolean isReproducingAudio = false;

    /**
     * @param music
     * @param sceneName
     * @param stageInitial
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     * @throws IOException
     */
    public void ChangeSceneUtil(
            Clip music,
            String sceneName,
            Stage stageInitial) throws UnsupportedAudioFileException {

        try {
            if (music != null) {
                music.stop();
            }
            FXMLLoader loader = getFxmlLoader(sceneName);
            Scene newScene = Utils.createScene(loader);
            Object sceneController = loader.getController();

            Label userName = (Label) newScene.lookup("#userName");
            if (userName != null) {
                userName.setText(StateManager.nombreUsuario);
            }

            PreguntaMultipleController controllerInstance = (PreguntaMultipleController) sceneController;
            controllerInstance.initialize(new PreguntaMultiple(), stageInitial, music, newScene);
            controllerInstance.setRespuestaCorrecta(Pregunta1.Egg.ordinal());
            controllerInstance.setNextScene();
            stageInitial.setScene(newScene);

            if (!isReproducingAudio && StateManager.audioReproduce) {

                ExecutorService executorService = Executors.newSingleThreadExecutor();

                executorService.execute(() -> {
                    AudioInputStream audioInput;
                    try {
                        audioInput = AudioSystem.getAudioInputStream(
                                Utils.downloadUsingStream("https://rainhearth.000webhostapp.com/SoundGam.wav"));
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInput);

                        double gain = .2D;
                        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
                        FloatControl controlVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        controlVolume.setValue(dB);
                        clip.start();
                    } catch (LineUnavailableException | IOException e) {
                        e.printStackTrace();
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    }
                    Utils.isReproducingAudio = true;
                });

                executorService.shutdown();
            }

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("Ha Ocurrido un error");
        }
    }

    /**
     * @param urlResource
     * @return
     * @throws IOException
     */
    public static InputStream downloadUsingStream(String urlResource) throws IOException {
        URL url = new URL(urlResource);
        InputStream is;
        try (BufferedInputStream bis = new BufferedInputStream(url.openStream())) {
            is = new ByteArrayInputStream(bis.readAllBytes());
        }
        return is;
    }

    /**
     * @param fxmlName
     * @return
     * @throws IOException
     */
    public FXMLLoader getFxmlLoader(
            String fxmlName) throws IOException {
        return new FXMLLoader(Utils.getInstance().getClass().getResource(fxmlName));
    }

    /**
     * @param fxmlLoader
     * @return
     * @throws IOException
     */
    public static Scene createScene(FXMLLoader fxmlLoader) throws IOException {
        Parent load = fxmlLoader.load();
        Scene scene = new Scene(load);
        return scene;
    }

    /**
     * @param loaderFxml
     * @return
     * @throws IOException
     */
    public static Object getController(FXMLLoader loaderFxml) throws IOException {
        return loaderFxml.getController();
    }

    public static void mostrarLoader(Stage escenario) throws InterruptedException {
        StackPane loadingRoot = new StackPane();
        final MFXProgressSpinner sp = new MFXProgressSpinner();
        Label txt = new Label("Cargando...");
        txt.setTextFill(Color.WHITE);
        loadingRoot.getChildren().add(sp);
        loadingRoot.getChildren().add(txt);
        Scene indicador = new Scene(loadingRoot);

        escenario.setTitle("Enigma");
        loadingRoot.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        escenario.setScene(indicador);
        escenario.show();
    }
}
