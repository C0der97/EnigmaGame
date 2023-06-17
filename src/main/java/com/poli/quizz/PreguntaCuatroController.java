/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author JHON SANCHEZ
 */
public class PreguntaCuatroController extends MultipleBaseController {

    Integer i = 0;

    public void setNextScene() {
        this.urlScene = "/fxml/SceneFive.fxml";
    }

    public void Contador(Scene escena) {
        Timeline tm = new Timeline();

        var contadorLbl = (Label) escena.lookup("#contador");

        //You can add a specific action when each frame is started.
        AnimationTimer anTm;
        anTm = new AnimationTimer() {
            @Override
            public void handle(long l) {
                String valor = String.valueOf(i / 100);
                contadorLbl.setText(valor);
                i++;
            }

        };

        EventHandler onFinished;
        onFinished = (EventHandler<ActionEvent>) (ActionEvent t) -> {
            //reset counter
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
            }

        };

        KeyFrame keyFrame;
        keyFrame = new KeyFrame(javafx.util.Duration.millis(20000), onFinished);

        //add the keyframe to the timeline
        tm.getKeyFrames().add(keyFrame);
        tm.play();
        anTm.start();
    }
}
