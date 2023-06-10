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
public class Utils {

    static boolean isReproducingAudio = false;

    public void ChangeSceneUtil(
            Clip music,
            String sceneName,
            Stage stageInitial) throws UnsupportedAudioFileException {

        try {
            if (music != null) {
                music.stop();
            }
            if (!isReproducingAudio && StateManager.audioReproduce) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(
                        Utils.downloadUsingStream("https://rainhearth.000webhostapp.com/toneGameEgypt.wav")
                );
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                Utils.isReproducingAudio = true;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            MFXThemeManager.addOn(newScene, Themes.DEFAULT, Themes.LEGACY);
            Object sceneController = loader.getController();
            
            Label userName = (Label) newScene.lookup("#userName");
            if(userName != null){
               userName.setText(StateManager.userName);
            }

            PreguntaMultipleController controllerInstance = (PreguntaMultipleController) sceneController;
            controllerInstance.initialize(new PreguntaMultiple(), stageInitial, music,newScene);
            controllerInstance.setRespuestaCorrecta(Pregunta1.Egg);
            controllerInstance.setNextScene();
            stageInitial.setScene(newScene);
        } catch (Exception e) {
            System.out.println("Ha Ocurrido un error");
        }
    }

    public static InputStream downloadUsingStream(String urlResource) throws IOException {
        URL url = new URL(urlResource);
        //File targetFile = new File("MoonKnight.wav");
        //OutputStream outStream = new FileOutputStream(targetFile);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        //outStream.write(bis.readAllBytes());
        InputStream is = new ByteArrayInputStream(bis.readAllBytes());
        bis.close();
        return is;
    }
}
