import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

import Lab1lib.*;

public class AnswerCardLoader {

    public ArrayList<AnswerCard> loadCardSetFromCsv(String fileName) {
        System.out.println("Loaded");
        int numberOfAnswers = 0;
        int questionNumber = 0;
        ArrayList<AnswerCard> loadedSet = new ArrayList<>();
        try {
            Scanner inputStream = new Scanner(new File(fileName));
            while (inputStream.hasNext()) {
                ArrayList<String> loadedAnswers = new ArrayList<>();
                String data = inputStream.next();
                String[] values = data.split(",");
                numberOfAnswers = Integer.parseInt(values[0]);
                questionNumber = Integer.parseInt(values[1]);
                for (int i = 2; i < values.length; i++) {
                    loadedAnswers.add(values[i]);
                }
                loadedSet.add(new AnswerCard(numberOfAnswers, questionNumber, loadedAnswers));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed while reading file");
        }
        return loadedSet;
    }

    public AnswerCard loadKeyFromCsv(String fileName) {

        int numberOfAnswers = 0;
        int questionNumber = 0;
        AnswerCard loadedKey = new AnswerCard(numberOfAnswers, questionNumber, null);
        try {
            Scanner inputStream = new Scanner(new File(fileName));
            while (inputStream.hasNext()) {
                ArrayList<String> loadedAnswers = new ArrayList<>();
                String data = inputStream.next();
                String[] values = data.split(",");
                numberOfAnswers = Integer.parseInt(values[0]);
                questionNumber = Integer.parseInt(values[1]);
                for (int i = 2; i < values.length; i++) {
                    loadedAnswers.add(values[i]);
                }
                loadedKey.setNumberOfAnswersInQuestion(numberOfAnswers);
                loadedKey.setNumberOfQuestions(questionNumber);
                loadedKey.setAnswers(loadedAnswers);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Failed while reading file");
        }
        return loadedKey;
    }
}
