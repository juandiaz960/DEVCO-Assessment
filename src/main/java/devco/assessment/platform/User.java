package main.java.devco.assessment.platform;

import java.io.Serializable;

/**
 * Un usuario que tiene nombre y puntaje para la prueba de aspirantes
 */
public class User implements Serializable{

    private String name; // Nombre del usuario
    private int score; // Puntaje del usuario

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Retorna el nombre del usuario
     * 
     * @return nombre del usuario
     */
    public String getName() {
        return this.name;
    }

    /**
     * Cambia el nombre del usuario
     * 
     * @param newName Nuevo nombre del usuario
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Retorna el puntaje del usuario
     * 
     * @return Puntaje del usuario
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Cambia el puntaje del usuario
     * 
     * @param newScore Nuevo puntaje del usuario
     */
    public void setScore(int newScore) {
        this.score = newScore;
    }

}
