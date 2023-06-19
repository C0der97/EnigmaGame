/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta2;

/**
 *
 * @author bare-
 */
public class PreguntaDosController  extends MultipleBaseController{
    

    public void setNextScene(){
        this.urlScene = "/fxml/SceneThree.fxml";
    }
  
    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta2.Abocado.ordinal());
    }
}
