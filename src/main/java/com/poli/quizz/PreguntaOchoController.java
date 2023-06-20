package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta4;

public class PreguntaOchoController extends MultipleBaseController {
    public void setNextScene() {
        this.contador = false;
        this.prevenirEjecucionDeTimer = true;
        this.setSonidoPregunta("https://rainhearth.000webhostapp.com/What%20goes%20up%20and%20doe.wav");

        this.urlScene = "/fxml/SceneNine.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta4.Clock.ordinal());
    }
}
