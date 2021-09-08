package main.java.devco.assessment;

import java.util.Scanner;
import java.util.ArrayList;
import main.java.devco.assessment.platform.*;

import main.java.devco.assessment.platform.Platform;

/**
 * La clase ScoreApplication es el main de la aplicación, usa un scanner para
 * interactuar con lo que digita el usuario, mostrar información y seguir el
 * flujo de la aplicación mostrando mensaje en pantakka
 */
public class ScoreApplication {

    private static Platform platform = new Platform();
    private static int on = 1;

    public static void main(String[] args) { 
        
        platform.loadRecords();

        Scanner scanner = new Scanner(System.in);
        System.out.println("----- PRUEBA PARA ASPIRANTES -----");
        while (on == 1) {
            System.out.println("Bienvenido. Por favor digite el tipo de usuario.");
            System.out.println("ADMIN: Digite la clave");
            System.out.println("Aspirante: Pulse cualquier tecla");
            System.out.println("Salir: Digite 9");
            System.out.println("> Digite su opcion: ");

            String option = scanner.nextLine();
            if (option.equals("0000")) {
                platform.setMode(1);
                System.out.println("Bienvenido ADMIN. Digite el numero de su opcion.");
                System.out.println("1. Agregar preguntas");
                System.out.println("2. Ver resultados de los aspirantes");
                System.out.println("3. Cargar preguntas por archivo");
                System.out.println("Cualquier otra tecla para salir");
                String adminOption = scanner.nextLine();
                while (adminOption.equals("1")) {
                    System.out.println("> Escriba el enunciado de la pregunta.");
                    String qStatement = scanner.nextLine();
                    System.out.println("> Escriba la respuesta a la pregunta (numeros o todo minusculas).");
                    String qAnswer = scanner.nextLine();
                    platform.addQuestion(qStatement, qAnswer);
                    System.out.println("> ¿Desea agregar otra pergunta? (1. Si, 0. No, 5. Guardar en archivo).");
                    adminOption = scanner.nextLine();
                }
                while (adminOption.equals("2")) {
                    System.out.println("Resultados de los aspirantes");
                    ArrayList<User> applicants = platform.getResults();
                    for (User applicant : applicants) {
                        System.out.println(applicant.getName() + ". "
                                + applicant.getScore() + " puntos.");
                    }
                    System.out.println("-- Fin de los resultados. --");
                    System.out.println("> Digite una opcion");
                    System.out.println("1. Agregar preguntas");
                    System.out.println("2. Ver resultados de los aspirantes");
                    System.out.println("Cualquier otra tecla para salir");
                    adminOption = scanner.nextLine();
                }
                if (adminOption.equals("3")) {
                    System.out.println("> Cargar: Escriba el nombre del archivo");
                    String qNameL = scanner.nextLine();
                    platform.loadQuestions(qNameL);
                }
                if (adminOption.equals("5")) {
                    System.out.println("> Guardar: Escriba el nombre del archivo");
                    String qNameS = scanner.nextLine();
                    platform.saveQuestions(qNameS);
                }
            } else if (option.equals("9")) {
                System.exit(0);
            } else {
                platform.setMode(2);
                platform.checkQuestions();
                System.out.println("> Porfavor ingrese su nombre:");
                String uName = scanner.nextLine();
                platform.addUser(uName);
                System.out.println(
                        "En este momento espezarán a aparacer las preguntas. Las respuestas son números o texto unicamente en minuscula");
                ArrayList<Question> questions = platform.startTest();
                int score = 0;
                int i = 1;
                for (Question question : questions) {
                    System.out.println("Pregunta " + i);
                    System.out.println(question.getStatement());
                    System.out.println("> Escriba la respuesta:");
                    String ans = scanner.nextLine();
                    if (ans.equals(question.getAnswer())) {
                        score += 4;
                    } else {
                        score -= 1;
                    }
                    i++;
                }
                platform.setUserScore(uName, score);
                platform.saveRecords();
                System.out.println("Gracias por tomar la prueba.");
                System.out.println("----------------------------");
            }
        }
    }
}