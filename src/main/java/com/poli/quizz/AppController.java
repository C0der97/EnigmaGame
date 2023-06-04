/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poli.quizz;

import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import models.PreguntaMultiple;

/**
 * FXML Controller class
 *
 * @author c0der97
 */
public class AppController {

    Stage stag;
    Clip music;
    
    public void setStage(Stage initialWindow) {
        this.stag = initialWindow;
    }

    public void setClip(Clip music) {
        this.music = music;
    }
    
    public void ChangeScene(ActionEvent ev) throws UnsupportedAudioFileException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SceneOne.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            this.music.stop();
            var sceneOneController = (PreguntaMultipleController) loader.getController();
            sceneOneController.initialize(new PreguntaMultiple());
            this.stag.setScene(newScene);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource("/audio/toneGameEgypt.wav").getPath()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
