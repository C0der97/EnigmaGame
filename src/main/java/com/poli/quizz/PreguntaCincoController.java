package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta4;

public class PreguntaCincoController extends MultipleBaseController {
        public void setNextScene() {
        this.contador = true;
        this.urlScene = "/fxml/SceneSix.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta4.Clock.ordinal());
    }
}
