package com.poli.quizz;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.poli.quizz.Enums.Pregunta7;

import javafx.scene.input.MouseEvent;

public class PreguntaSieteController extends MultipleBaseController {
    public void setNextScene() {
        this.setSonidoPregunta("https://rainhearth.000webhostapp.com/What%20has%20a%20thumb%20and.wav");
        this.contador = false;
        this.prevenirEjecucionDeTimer = true;
        this.urlScene = "/fxml/SceneEight.fxml";
    }

    @Override
    public void setRespuestaCorrecta(int respuesta) {
        super.setRespuestaCorrecta(Pregunta7.Bottle.ordinal());
    }

      public void reproduceSonido(MouseEvent event) throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        this.reproducirSonidoNuevamente();
      }
}
