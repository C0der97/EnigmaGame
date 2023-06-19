package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta4;

public class PeguntaNueveController extends MultipleBaseController {
        public void setNextScene() {
        this.setSonidoPregunta("");
        this.contador = false;
        this.urlScene = "/fxml/EndGame.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta4.Clock.ordinal());
    }
}
