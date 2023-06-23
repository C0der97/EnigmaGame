package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta6;

public class PreguntaSeisController extends MultipleBaseController {
        public void setNextScene() {
        this.setSonidoPregunta("https://rainhearth.000webhostapp.com/What%20has%20a%20neck%20but%20.wav");
        this.contador = true;
        this.urlScene = "/fxml/SceneSeven.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta6.Witch.ordinal());
    }
}
