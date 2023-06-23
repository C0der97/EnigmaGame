/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta4;

/**
 *
 * @author JHON SANCHEZ
 */
public class PreguntaCuatroController extends MultipleBaseController {


    public void setNextScene() {
        this.contador = true;
        this.urlScene = "/fxml/SceneFive.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        StateManager.RespuestasCorrectas = 0;
        StateManager.CantidadRespuestas = 0;
        super.setRespuestaCorrecta(Pregunta4.Clock.ordinal());
    }
}
