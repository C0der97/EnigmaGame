package com.poli.quizz;

public class EndGameController extends MultipleBaseController {

        @Override
        public void setNextScene() {
        this.contador = true;
        this.urlScene = "/fxml/App.fxml";
    }
    
}
