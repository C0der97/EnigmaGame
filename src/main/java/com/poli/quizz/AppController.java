/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poli.quizz;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * FXML Controller class
 *
 * @author c0der97
 */
public class AppController {

    Stage escenario;
    Clip musica;
    Scene escenaPrincipal;

    /**
     * Inicializa la ventana
     */
    public void setStage(Stage escenarioInicial) {
        this.escenario = escenarioInicial;
    }

    /**
     * asigna el clip de musica
     */
    public void setClip(Clip musica) {
        this.musica = musica;
    }

    /**
     * asigna la escena principal
     */
    public void setScene(Scene escenaPrincipal) {
        this.escenaPrincipal = escenaPrincipal;
    }

    /**
     * Cambia la escena de la ventana
     *
     */
    public void ChangeScene(ActionEvent ev) throws UnsupportedAudioFileException, IOException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            try {
                Platform.runLater(() -> {
                    try {
                        MFXTextField userName = (MFXTextField) this.escenaPrincipal.lookup("#userName");
                        StateManager.nombreUsuario = userName.getText();

                        if(StateManager.nombreUsuario != ""){
                                        Utils.mostrarLoader(this.escenario);
                                                    this.escenario.close();
                                                 Utils instanciaUtils = Utils.getInstance();
                        FXMLLoader cargaFxml = instanciaUtils.getFxmlLoader("/fxml/App.fxml");
                        Scene escena = Utils.createScene(cargaFxml);
                        AppController controllerInitial = (AppController) cargaFxml.getController();
                        controllerInitial.setStage(this.escenario);
                        this.escenario.setScene(escena);
                        this.escenario.show();
                        }else{
                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Nombre de usuario Obligatorio!! ");
            alert.showAndWait();
                        }

   
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    /**
     * Siguiente escena
     */
    public void NextScene(ActionEvent ev) throws UnsupportedAudioFileException, InterruptedException {
        Utils utilidades = Utils.getInstance();
        Utils.mostrarLoader(this.escenario);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            try {
                if (this.musica != null) {
                    this.musica.stop();
                }
                Thread.sleep(2000);
                Platform.runLater(() -> {
                    try {
                        utilidades.ChangeSceneUtil(this.musica, "/fxml/SceneOne.fxml", this.escenario);
                    } catch (UnsupportedAudioFileException ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        });

    }

}
