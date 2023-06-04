/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta1;
import com.poli.quizz.Enums.Pregunta2;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            if (music != null) {
                music.stop();
            }
            Object sceneController = loader.getController();
            changeSceneByClass(sceneController, music, stageInitial, newScene);

            if (!isReproducingAudio && StateManager.audioReproduce) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource("/toneGameEgypt.wav").getPath()));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                Utils.isReproducingAudio = true;
            }

        } catch (Exception e) {
            System.out.println("Ha Ocurrido un error");
        }
    }

    void changeSceneByClass(
            Object controllerData,
            Clip music,
            Stage stageInitial,
            Scene nuevaScene) {

        var controllerClass = controllerData.getClass().toString();

        switch (controllerClass) {
            case "class com.poli.quizz.PreguntaMultipleController":
                PreguntaMultipleController controllerInstance = (PreguntaMultipleController) controllerData;
                controllerInstance.initialize(new PreguntaMultiple(), stageInitial, music);
                controllerInstance.setRespuestaCorrecta(Pregunta1.Egg);
                controllerInstance.setNextScene();
                break;

            case "class com.poli.quizz.PreguntaDosController":
                PreguntaDosController controllerInstanceTwo = (PreguntaDosController) controllerData;
                controllerInstanceTwo.initialize(new PreguntaMultiple(), stageInitial, music);
                controllerInstanceTwo.setRespuestaCorrecta(Pregunta2.Abocado);
                controllerInstanceTwo.setNextScene();
                break;

            default:
                throw new AssertionError();
        }
        stageInitial.setScene(nuevaScene);

    }
}
