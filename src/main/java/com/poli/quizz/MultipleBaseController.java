/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta2;
import com.poli.quizz.Enums.Pregunta3;
import com.poli.quizz.Enums.Pregunta4;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
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

    private PreguntaMultiple modelo;

    Stage stag;
    Clip music;
    Scene mainScene;

    String urlScene = "";

/*
Inicializa controlador base
*/    
    /**
     * @param model
     * @param initialWindow
     * @param music
     * @param main_Scene
     */
    public void initialize(PreguntaMultiple model, Stage initialWindow, Clip music,
            Scene main_Scene) {
        this.modelo = model;
        this.music = music;
        this.stag = initialWindow;
        this.mainScene = main_Scene;
        Label lblPuntos = (Label) main_Scene.lookup("#puntos");
        if (lblPuntos != null) {
            lblPuntos.setText(Integer.toString(StateManager.Puntos));
        }
        Label userName = (Label) main_Scene.lookup("#userName");
        if (userName != null) {
            userName.setText(StateManager.nombreUsuario);
        }
    }

    void setRespuestaCorrecta(int respuesta) {
        this.modelo.setRespuestaCorrecta(respuesta);
    }

    /*
     * Seleccionar opcion de respuesta
     */
    /**
     * @param event
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void onClickOption(MouseEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Pane optPane = (Pane) event.getSource();
        int optionSelected = Integer.parseInt(optPane.getId());
        ImageView opt = (ImageView) optPane.getChildren().get(0);
        opt.setStyle("-fx-opacity: 0.5");

        if (this.modelo.checkRespuestaCorrecta(optionSelected)) {
            StateManager.Puntos += 10; // Sumar 10 puntos por respuesta correcta
            StateManager.RespuestasCorrectas++;
            reproducirSonidoPorRespuesta("https://rainhearth.000webhostapp.com/passed.wav");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Respuesta Correcta!! ");
            alert.showAndWait();
        } else {
            StateManager.Puntos -= 10; // Restar 10 puntos por respuesta incorrecta
            StateManager.RespuestasCorrectas--;
            reproducirSonidoPorRespuesta("https://rainhearth.000webhostapp.com/wrong.wav");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Respuesta Incorrecta!! ");
            alert.showAndWait();
        }
        this.changeScene();
    }

    void changeScene() throws IOException {
        FXMLLoader loader = Utils.getInstance().getFxmlLoader(this.urlScene);;
        Scene newScene = Utils.createScene(loader);

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
