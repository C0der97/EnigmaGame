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
import javafx.scene.Parent;
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

    Stage stag;
    Clip music;
    Scene mainScene;
    
    public void setStage(Stage initialWindow) {
        this.stag = initialWindow;
    }

    public void setClip(Clip music) {
        this.music = music;
    }
    
    public void setScene(Scene mainS) {
        this.mainScene = mainS;
    }
    
    public void ChangeScene(ActionEvent ev) throws UnsupportedAudioFileException, IOException {
            MFXTextField userName = (MFXTextField) this.mainScene.lookup("#userName");
            StateManager.userName = userName.getText();
            FXMLLoader loadr = new FXMLLoader(getClass().getResource("/fxml/App.fxml"));
            Parent load = loadr.load();
            var scene = new Scene(load);
            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
            var controllerInitial = (AppController) loadr.getController();
            controllerInitial.setStage(this.stag);
            this.stag.setScene(scene);
            this.stag.show();
    }
    
    public void NextScene(ActionEvent ev) throws UnsupportedAudioFileException{
        Utils utilidades =new Utils();
        utilidades.ChangeSceneUtil(this.music, "/fxml/SceneOne.fxml",this.stag);
    }

}
