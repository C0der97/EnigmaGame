/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta3;

/**
 *
 * @author bare-
 */
public class PreguntaTresController extends MultipleBaseController {
    public void setNextScene() {
        this.contador = true;
        this.urlScene = "/fxml/SceneFour.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta3.Knife.ordinal());
    }
}
