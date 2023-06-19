package com.poli.quizz;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.PreguntaMultiple;

public interface IMultipleQuestions {
    void initialize(PreguntaMultiple model, Stage initialWindow, Clip music,
            Scene main_Scene) throws UnsupportedAudioFileException, IOException, LineUnavailableException;

    void setRespuestaCorrecta(int respuesta);

    void onClickOption(MouseEvent event)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException;

    void changeScene() throws IOException, UnsupportedAudioFileException, LineUnavailableException;

    AudioInputStream reproducirSonidoPorRespuesta(String nombreSonido)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException;

    void setNextScene();

    void Contador(Scene escena);
}
