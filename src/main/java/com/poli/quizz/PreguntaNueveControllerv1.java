package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta9;

public class PreguntaNueveControllerv1 extends MultipleBaseController {
        public void setNextScene() {
        this.setSonidoPregunta("");
        this.contador = false;
        this.prevenirEjecucionDeTimer = true;
        this.urlScene = "/fxml/EndGame.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta9.Age.ordinal());
    }
}
