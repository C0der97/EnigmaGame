package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta5;

public class PreguntaCincoController extends MultipleBaseController {
        public void setNextScene() {
        this.contador = true;
        this.urlScene = "/fxml/SceneSix.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta5.Bell.ordinal());
    }
}
