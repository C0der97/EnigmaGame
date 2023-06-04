/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bare-
 */
public class PreguntaMultiple{
    
    
    private int RespuestaCorrecta = 0;
    
    public void setRespuestaCorrecta(int respuestaCorrecta){
        this.RespuestaCorrecta = respuestaCorrecta;
    }
    
    public boolean checkRespuestaCorrecta(int respuesta){
        return RespuestaCorrecta == respuesta;
    }
}
