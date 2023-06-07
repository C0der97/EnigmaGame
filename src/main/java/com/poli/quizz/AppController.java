/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poli.quizz;

import javafx.event.ActionEvent;
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
    
    public void ChangeScene(ActionEvent ev) throws UnsupportedAudioFileException {
        Utils utilidades =new Utils();
        utilidades.ChangeSceneUtil(this.music, "/fxml/SceneOne.fxml",this.stag);
    }

}
