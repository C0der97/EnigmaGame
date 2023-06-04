/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta2;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            reproducirSonidoPorRespuesta("/passed.wav");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Respuesta Correcta!! ");
            alert.showAndWait();
        } else {
            reproducirSonidoPorRespuesta("/wrong.wav");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Respuesta Incorrecta!! ");
            alert.showAndWait();
        }

        this.changeScene();
    }

    void changeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(this.urlScene));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        if (this.music != null) {
            this.music.stop();
        }
        Object sceneController = loader.getController();

        var controllerClass = sceneController.getClass().toString();

        switch (controllerClass) {
            case "class com.poli.quizz.PreguntaDosController":
                PreguntaDosController controllerInstanceTwo = (PreguntaDosController) sceneController;
                controllerInstanceTwo.initialize(new PreguntaMultiple(), this.stag, music);
                controllerInstanceTwo.setRespuestaCorrecta(Pregunta2.Abocado);
                controllerInstanceTwo.setNextScene();
                break;

            default:
                throw new AssertionError();
        }
        this.stag.setScene(newScene);
    }

    void reproducirSonidoPorRespuesta(String nombreSonido) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (StateManager.audioReproduce) {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(getClass().getResource(nombreSonido).getPath()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        }
    }
}
