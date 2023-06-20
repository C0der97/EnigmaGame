package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta4;

public class PreguntaSieteController extends MultipleBaseController {
    public void setNextScene() {
        this.setSonidoPregunta("https://rainhearth.000webhostapp.com/What%20has%20a%20thumb%20and.wav");
        this.contador = false;
        this.prevenirEjecucionDeTimer = true;
        this.urlScene = "/fxml/SceneEight.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta4.Clock.ordinal());
    }
}
