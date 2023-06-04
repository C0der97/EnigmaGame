/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta1;
import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource("/toneGameEgypt.wav").getPath()));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                Utils.isReproducingAudio = true;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Object sceneController = loader.getController();

            PreguntaMultipleController controllerInstance = (PreguntaMultipleController) sceneController;
            controllerInstance.initialize(new PreguntaMultiple(), stageInitial, music);
            controllerInstance.setRespuestaCorrecta(Pregunta1.Egg);
            controllerInstance.setNextScene();
            stageInitial.setScene(newScene);
        } catch (Exception e) {
            System.out.println("Ha Ocurrido un error");
        }
    }
}
