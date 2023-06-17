/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poli.quizz;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
     * Cambia la escena
     * de la
     * ventana
     * */
    public void ChangeScene(ActionEvent ev) throws UnsupportedAudioFileException, IOException {
            this.escenario.close();
            MFXTextField userName = (MFXTextField) this.escenaPrincipal.lookup("#userName");
            StateManager.nombreUsuario = userName.getText();

            Utils instanciaUtils = Utils.getInstance();
            FXMLLoader cargaFxml = instanciaUtils.getFxmlLoader("/fxml/App.fxml");
            Scene escena = Utils.createScene(cargaFxml);
            MFXThemeManager.addOn(escena, Themes.DEFAULT, Themes.LEGACY);
            AppController controllerInitial = (AppController) cargaFxml.getController();
            controllerInitial.setStage(this.escenario);
            this.escenario.setScene(escena);
            this.escenario.show();
    }
    
    /**
     * Siguiente escena
     */
    public void NextScene(ActionEvent ev) throws UnsupportedAudioFileException{
        Utils utilidades = Utils.getInstance();
        utilidades.ChangeSceneUtil(this.musica, "/fxml/SceneOne.fxml",this.escenario);
    }

}
