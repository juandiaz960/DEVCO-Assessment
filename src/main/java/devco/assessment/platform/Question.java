package main.java.devco.assessment.platform;

import java.io.Serializable;

/**
 * Una pregunta para la prueba de aspirantes formada por un enunciado y una respuesta
 */
public class Question implements Serializable{

    private String statement; //Enunciado de la pregunta
    private String answer; //Respuesta de la pregunta

    public Question(String statement, String answer){
        this.statement = statement;
        this.answer = answer;
    }

    /**
     * Retorna el enunciado de la pregunta
     * @return enunciado de la pregunta
     */
    public String getStatement(){
        return this.statement;
    }
    
    /**
     * Cambia el enunciado a la pregunta
     * @param newStatement Nuevo enunciado
     */
    public void setStatement(String newStatement){
        this.statement = newStatement;        
    }

    /**
     * Retorna la respuesta de la pregunta
     * @return respuesta de la pregunta
     */
    public String getAnswer(){
        return this.answer;
    }

    /**
     * Cambia la respuesta a la pregunta
     * @param newAnswer Nueva respuesta
     */
    public void setAnswer(String newAnswer){
        this.answer = newAnswer;        
    }
    
}
