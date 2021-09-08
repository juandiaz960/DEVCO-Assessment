package main.java.devco.assessment.platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La clase Platform es el sistema de aplicaciones que contiene las preguntas y
 * los usuarios. Se encarga de validaciones, agregar preguntas, usuarios y poner
 * los puntajes de acuerdo al desempeño en la prueba.
 */

public class Platform implements Serializable {

    private ArrayList<Question> questions; // Arreglo de preguntas
    private ArrayList<User> applicants; // Arreglo de aspirantes
    private int mode; // Un modo qué define acciones y sirve para validaciones

    public Platform() {
        questions = new ArrayList<Question>();
        applicants = new ArrayList<User>();
        mode = 0;
    }

    /**
     * Retorna el total de preguntas disponibles
     * 
     * @return El total de preguntas en el arreglo questions
     */
    public int getQuestionQuantity() {
        return questions.size();
    }

    /**
     * Valida si hay el mínimo de preguntas requeridas
     */
    public void checkQuestions() {
        if (questions.size() < 8) {
            System.out.println("No hay suficientes preguntas disponibles. Intente más tarde");
            System.exit(0);
        } else {
            System.out.println("Bienvenido al test.");
        }
    }

    /**
     * Cambia el modo actual de Platform
     * 
     * @param newMode el modo es un entero. 1 para administrador, 2 para aspirante
     */
    public void setMode(int newMode) {
        this.mode = newMode;
    }

    /**
     * Agrega una pregunta al arreglo de preguntas. Valida que esté en modo
     * administrador
     * 
     * @param statement Enunciado de la pregunta
     * @param answer    Respuesta a la pregunta
     */
    public void addQuestion(String statement, String answer) {
        if (this.mode == 1) {
            Question newQuestion = new Question(statement, answer);
            questions.add(newQuestion);
            System.out.println("Pregunta agregada correctamente.");
            System.out.println("Preguntas registradas actualmente: " + getQuestionQuantity());
        } else {
            System.out.println("No tiene permiso para crear preguntas.");
        }
    }

    /**
     * Agrega un usuario al arreglo de usuarios. Valida que este en modo de
     * aspirante
     * 
     * @param name Nombre del usuario a registrar
     */
    public void addUser(String name) {
        if (this.mode == 2) {
            User newUser = new User(name);
            applicants.add(newUser);
            System.out.println("Usted se ha registrado correctamente.");
        } else {
            System.out.println("No tiene permiso para acceder al test.");
        }
    }

    /**
     * Retorna el arreglo de preguntas
     * 
     * @return Arreglo de preguntas
     */
    public ArrayList<Question> startTest() {
        return questions;
    }

    /**
     * Retorna el arreglo de usuarios
     * 
     * @return Arreglo de aspirantes
     */
    public ArrayList<User> getResults() {
        return applicants;
    }

    /**
     * Le pone a un determinado usuario su respectivo puntaje
     * 
     * @param user  El usuario que se va a puntuar
     * @param score El puntaje resultado
     */
    public void setUserScore(String user, int score) {
        for (User applicant : applicants) {
            if ((applicant.getName().equals(user))) {
                applicant.setScore(score);
            }
        }
        System.out.println("-- Fin prueba --");
        System.out.println(user + ". " + score + " puntos.");
    }

    /**
     * Carga las preguntas desde un archivo especificado para ser usadas en la
     * prueba
     * 
     * @param name Nombre del archivo a cargar
     */
    public void loadQuestions(String name) {
        try {
            FileInputStream fis = new FileInputStream("res/" + name + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            questions = (ArrayList<Question>) ois.readObject();
            ois.close();
            System.out.println("Preguntas cargadas del archivo res/" + name + ".ser");
            System.out.println("--------------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo especificado no existe");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda las preguntas a un archivo especificado
     * 
     * @param name Nombre del archivo a guardar
     */
    public void saveQuestions(String name) {
        try {
            FileOutputStream fos = new FileOutputStream("res/" + name + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(questions);
            oos.close();
            System.out.println("Preguntas guardadas en archivo res/" + name + ".ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los resultados de los estudiantes que hayan realizado la prueba.
     */
    public void loadRecords() {
        try {
            FileInputStream fis = new FileInputStream("res/records.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            applicants = (ArrayList<User>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo especificado no existe");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda los resltados de los estudiantes que hayan realizado la prueba.
     */
    public void saveRecords() {
        try {
            FileOutputStream fos = new FileOutputStream("res/records.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(applicants);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
