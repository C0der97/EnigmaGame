/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta2;
import com.poli.quizz.Enums.Pregunta3;
import com.poli.quizz.Enums.Pregunta4;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    Scene mainScene;

    String urlScene = "";

    private Utils Utilidades;

    public void initialize(PreguntaMultiple model, Stage initialWindow, Clip music,
            Scene main_Scene) {
        this.model = model;
        this.music = music;
        this.stag = initialWindow;
        this.Utilidades = new Utils();
        this.mainScene = main_Scene;
        Label lblPuntos = (Label) main_Scene.lookup("#puntos");
        if (lblPuntos != null) {
            lblPuntos.setText(Integer.toString(StateManager.Puntos));
        }
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
        StateManager.Puntos += 1; // Sumar 1 punto por respuesta correcta
        StateManager.RespuestasCorrectas++;
        reproducirSonidoPorRespuesta("https://rainhearth.000webhostapp.com/passed.wav");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Respuesta Correcta!! ");
        alert.showAndWait();
    } else {
        StateManager.Puntos -= 1; // Restar 1 punto por respuesta incorrecta
        StateManager.RespuestasCorrectas--;
        reproducirSonidoPorRespuesta("https://rainhearth.000webhostapp.com/wrong.wav");
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
                controllerInstanceTwo.initialize(new PreguntaMultiple(), this.stag, music, newScene);
                controllerInstanceTwo.setRespuestaCorrecta(Pregunta2.Abocado);
                controllerInstanceTwo.setNextScene();
                break;
            case "class com.poli.quizz.PreguntaTresController":
                PreguntaTresController controllerInstanceThree = (PreguntaTresController) sceneController;
                controllerInstanceThree.initialize(new PreguntaMultiple(), this.stag, music, newScene);
                controllerInstanceThree.setRespuestaCorrecta(Pregunta3.Knife);
                controllerInstanceThree.setNextScene();
                break;
            case "class com.poli.quizz.PreguntaCuatroController":
                PreguntaCuatroController controllerInstanceFour = (PreguntaCuatroController) sceneController;
                controllerInstanceFour.initialize(new PreguntaMultiple(), this.stag, music, newScene);
                controllerInstanceFour.setRespuestaCorrecta(Pregunta4.Clock);
                controllerInstanceFour.setNextScene();
                break;
            default:
                throw new AssertionError();
        }
        this.stag.setScene(newScene);
    }

    void reproducirSonidoPorRespuesta(String nombreSonido) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (StateManager.audioReproduce) {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(Utils.downloadUsingStream(nombreSonido));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        }
    }
}
