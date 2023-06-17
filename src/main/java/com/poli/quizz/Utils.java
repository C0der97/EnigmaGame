/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta1;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import java.io.*;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
     * @param urlResource
     * @return
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
            MFXThemeManager.addOn(newScene, Themes.DEFAULT, Themes.LEGACY);
            Object sceneController = loader.getController();
            
            Label userName = (Label) newScene.lookup("#userName");
            if(userName != null){
               userName.setText(StateManager.nombreUsuario);
            }

            PreguntaMultipleController controllerInstance = (PreguntaMultipleController) sceneController;
            controllerInstance.initialize(new PreguntaMultiple(), stageInitial, music,newScene);
            controllerInstance.setRespuestaCorrecta(Pregunta1.Egg);
            controllerInstance.setNextScene();
            stageInitial.setScene(newScene);

            if (!isReproducingAudio && StateManager.audioReproduce) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(
                        Utils.downloadUsingStream("https://rainhearth.000webhostapp.com/toneGameEgypt.wav")
                );
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                Utils.isReproducingAudio = true;
            }

        } catch (Exception e) {
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
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        InputStream is = new ByteArrayInputStream(bis.readAllBytes());
        bis.close();
        return is;
    }

    /**
     * @param fxmlName
     * @param classData
     * @return
     * @throws IOException
     */
    public FXMLLoader getFxmlLoader(
        String fxmlName) throws IOException {
           return new FXMLLoader(Utils.getInstance().getClass().getResource(fxmlName));
    }

    /**
     * @param sceneName
     * @param stageInitial
     * @param classData
     * @return
     * @throws IOException
     */
    public static Scene createScene(FXMLLoader fxmlLoader) throws IOException {
        Parent load = fxmlLoader.load();
        Scene scene = new Scene(load);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
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
}
