package com.poli.quizz;

import com.poli.quizz.Enums.Pregunta9;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EndGameController extends MultipleBaseController {

        @Override
        public void setNextScene() {
        this.contador = true;
        this.urlScene = "/fxml/App.fxml";
    }
        @Override
    public void setRespuestaCorrecta(int respuesta) {
        Label labelPuntos = (Label) this.escenaActual.lookup("#PuntosFinales");
        if(labelPuntos!=null){
            labelPuntos.setText(String.valueOf(StateManager.Puntos));
        }
        ImageView imgPerdedor = (ImageView) this.escenaActual.lookup("#Lose");
        ImageView imgGanador = (ImageView) this.escenaActual.lookup("#Win");
        imgPerdedor.setVisible(StateManager.Puntos < 50);
        imgGanador.setVisible(StateManager.Puntos >= 50);
        
    }
    
    
    
}
