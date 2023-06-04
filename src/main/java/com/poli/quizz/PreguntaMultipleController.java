/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import models.PreguntaMultiple;

/**
 *
 * @author c0der97
 */
public class PreguntaMultipleController {

    private PreguntaMultiple model;

    void initialize(PreguntaMultiple model) {
        this.model = model;
        this.model.setRespuestaCorrecta(1);
    }

    public void onClickOption(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Pane optPane = (Pane) event.getSource();
        int optionSelected = Integer.parseInt(optPane.getId());
        ImageView opt = (ImageView) optPane.getChildren().get(0);
        opt.setStyle("-fx-opacity: 0.5");

        if (this.model.checkRespuestaCorrecta(optionSelected)) {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource("/audio/passed.wav").getPath()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Respuesta Correcta!! ");
            alert.showAndWait();
        } else {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource("/audio/wrong.wav").getPath()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Respuesta Incorrecta!! ");
            alert.showAndWait();
        }

    }
}
