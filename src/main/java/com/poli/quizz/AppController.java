/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.poli.quizz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AppController {
    
 public void Click(ActionEvent ev){
     System.out.println("asdasd");
     Alert alert = new Alert(AlertType.CONFIRMATION, "Delete ", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    //do stuff
}
 }
 
}
