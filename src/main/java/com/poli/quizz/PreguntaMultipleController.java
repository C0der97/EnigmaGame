/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta1;
import com.poli.quizz.Enums.Pregunta2;

/**
 *
 * @author c0der97
 */
public class PreguntaMultipleController extends MultipleBaseController {

    public void setNextScene() {
        this.urlScene = "/fxml/SceneTwo.fxml";
    }


    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta1.Egg.ordinal());
    }

}
