/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.github.palexdev.materialfx.controls.MFXTextField;
import models.PreguntaMultiple;

/**
 *
 * @author bare-
 */
public class MultipleBaseController implements IMultipleQuestions {

    private PreguntaMultiple modelo;

    Stage stag;
    Clip music;
    AudioInputStream respuestaCorrectaSonido;
    AudioInputStream respuestaIncorrectaSonido;
    Scene escenaActual;
    boolean prevenirEjecucionDeTimer = false;
    String urlScene = "";
    Boolean contador = false;
    Integer segundos = 0;
    String sonidoPregunta = "";
    AudioInputStream audioPregunta = null;

    /*
     * Inicializa controlador base
     */
    /**
     * @param model
     * @param initialWindow
     * @param music
     * @param main_Scene
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     * @throws java.io.IOException
     * @throws javax.sound.sampled.LineUnavailableException
     */
    public void initialize(PreguntaMultiple model, Stage initialWindow, Clip music,
            Scene main_Scene) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.modelo = model;
        this.music = music;
        this.escenaActual = main_Scene;
        this.stag = initialWindow;
        Label lblPuntos = (Label) main_Scene.lookup("#puntos");
        if (lblPuntos != null) {
            lblPuntos.setText(Integer.toString(StateManager.Puntos));
        }
        Label userName = (Label) main_Scene.lookup("#userName");
        if (userName != null) {
            userName.setText(StateManager.nombreUsuario);
        }
        this.respuestaCorrectaSonido = reproducirSonidoPorRespuesta("https://rainhearth.000webhostapp.com/passed.wav");
        this.respuestaIncorrectaSonido = reproducirSonidoPorRespuesta("https://rainhearth.000webhostapp.com/wrong.wav");
    }

    public void setSonidoPregunta(String sonidoPregunta) {
        this.sonidoPregunta = sonidoPregunta;
    }

    public void setRespuestaCorrecta(int respuesta) {
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
     * @throws java.lang.InterruptedException
     */
    public void onClickOption(MouseEvent event)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        Pane optPane = (Pane) event.getSource();
        int optionSelected = Integer.parseInt(optPane.getId());
        ImageView opt = (ImageView) optPane.getChildren().get(0);
        opt.setStyle("-fx-opacity: 0.5");

        StateManager.CantidadRespuestas++;

        if (this.modelo.checkRespuestaCorrecta(optionSelected)) {
            StateManager.Puntos += 10; // Sumar 10 puntos por respuesta correcta
            StateManager.RespuestasCorrectas++;
            if (this.respuestaCorrectaSonido != null) {
                Clip sonido = AudioSystem.getClip();
                sonido.open(this.respuestaCorrectaSonido);
                sonido.start();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Respuesta Correcta!! ");
            alert.showAndWait();
        } else {
            if (this.respuestaIncorrectaSonido != null) {
                Clip sonido = AudioSystem.getClip();
                sonido.open(this.respuestaIncorrectaSonido);
                sonido.start();
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, "Respuesta Incorrecta!! ");
            alert.showAndWait();
        }
        // Utils.mostrarLoader(this.stag);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Platform.runLater(() -> {
                try {
                    this.changeScene();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    System.out.println(Arrays.toString(ex.getStackTrace()));
                }
            });
        });

    }

    public void changeScene() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        if(StateManager.RespuestasCorrectas < 2 && StateManager.CantidadRespuestas > 2){
            this.urlScene = "/fxml/EndGame.fxml";
            Alert alert = new Alert(AlertType.ERROR, "Perdiste");
            alert.showAndWait();
        }

        FXMLLoader loader = Utils.getInstance().getFxmlLoader(this.urlScene);
        Scene newScene = Utils.createScene(loader);

        if (this.music != null) {
            this.music.stop();
        }
        IMultipleQuestions controlador = (IMultipleQuestions) loader.getController();
        controlador.initialize(new PreguntaMultiple(), this.stag, music, newScene);
        controlador.setRespuestaCorrecta(10);
        controlador.setNextScene();

        if (sonidoPregunta != "") {
            this.descargarSonidoPregunta();
            this.reproducirSonidoRespuesta();
        }

        if (this.contador) {
            controlador.Contador(newScene);
            prevenirEjecucionDeTimer = true;
        }

        this.stag.setScene(newScene);

                if(StateManager.CantidadRespuestas >= 2 && StateManager.RespuestasCorrectas <= 2){
                                    ImageView imgPerdedor = (ImageView) newScene.lookup("#Lose");
                                 imgPerdedor.setVisible(true);

        }else{
                                   ImageView imgPerdedor = (ImageView) newScene.lookup("#Win");
                                 imgPerdedor.setVisible(true);
        }
    }

    public AudioInputStream reproducirSonidoPorRespuesta(String nombreSonido)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioEffecto = null;
        audioEffecto = AudioSystem.getAudioInputStream(Utils.downloadUsingStream(nombreSonido));
        return audioEffecto;
    }

    public void setNextScene() {
        throw new UnsupportedOperationException("No implementado'");
    }

    public void Contador(Scene escena) {
        Timeline tm = new Timeline();

        Label contadorLbl = (Label) escena.lookup("#contador");

        if (contadorLbl == null) {
            return;
        }

        // You can add a specific action when each frame is started.
        AnimationTimer anTm;
        anTm = new AnimationTimer() {
            @Override
            public void handle(long l) {
                String valor = String.valueOf(segundos / 100);
                contadorLbl.setText(valor);
                segundos++;
            }

        };

        EventHandler onFinished;
        onFinished = (EventHandler<ActionEvent>) (ActionEvent t) -> {
            // reset counter
            anTm.stop();

            if (!this.prevenirEjecucionDeTimer) {
                StackPane loadingRoot = new StackPane();
                Label txt = new Label("Tiempo finalizado siguiente pregunta...");
                txt.setTextFill(Color.WHITE);

                loadingRoot.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
                loadingRoot.getChildren().add(txt);
                Scene mensaje = new Scene(loadingRoot);
                this.stag.setScene(mensaje);
                this.stag.show();

                try {
                    Thread.sleep(4000);
                    this.changeScene();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        KeyFrame keyFrame;
        keyFrame = new KeyFrame(javafx.util.Duration.millis(20000), onFinished);

        // add the keyframe to the timeline
        tm.getKeyFrames().add(keyFrame);
        tm.play();
        anTm.start();
    }

    /**
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    public void descargarSonidoPregunta() throws UnsupportedAudioFileException, IOException {
        audioPregunta = AudioSystem.getAudioInputStream(Utils.downloadUsingStream(this.sonidoPregunta));
    }

    /**
     * @throws LineUnavailableException
     * @throws IOException
     */
    public void reproducirSonidoRespuesta() throws LineUnavailableException, IOException {
        Clip sonido = AudioSystem.getClip();
        sonido.open(this.audioPregunta);
        sonido.start();
    }

    public void reproducirSonidoNuevamente() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
                if (this.sonidoPregunta != "") {
            this.descargarSonidoPregunta();
            this.reproducirSonidoRespuesta();
        }
    }
}
