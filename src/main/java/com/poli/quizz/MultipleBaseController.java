/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import models.PreguntaMultiple;

/**
 *
 * @author bare-
 */
public class MultipleBaseController {

    private PreguntaMultiple model;

    Stage stag;
    Clip music;

    String urlScene = "";

    private Utils Utilidades;

    void initialize(PreguntaMultiple model, Stage initialWindow, Clip music) {
        this.model = model;
        this.music = music;
        this.stag = initialWindow;
        this.Utilidades = new Utils();
    }

    void setRespuestaCorrecta(int respuesta) {
        this.model.setRespuestaCorrecta(respuesta);
    }

    public void onClickOption(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Pane optPane = (Pane) event.getSource();
        int optionSelected = Integer.parseInt(optPane.getId());
        ImageView opt = (ImageView) optPane.getChildren().get(0);
        opt.setStyle("-fx-opacity: 0.5");
        if (this.model.checkRespuestaCorrecta(optionSelected)) {
            reproducirSonidoPorRespuesta("/audio/passed.wav");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Respuesta Correcta!! ");
            alert.showAndWait();
        } else {
            reproducirSonidoPorRespuesta("/audio/wrong.wav");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Respuesta Incorrecta!! ");
            alert.showAndWait();
        }

        this.Utilidades.ChangeSceneUtil(this.music, urlScene, this.stag);

    }

    void reproducirSonidoPorRespuesta(String nombreSonido) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource(nombreSonido).getPath()));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();

    }
}
