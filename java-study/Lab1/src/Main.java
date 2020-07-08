
import Lab1lib.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private AnswerCardLoader loader = new AnswerCardLoader();
    private CardsSet cardsSet = new CardsSet(new ArrayList<AnswerCard>(), new AnswerCard(0, 0, new ArrayList<String>()));

    private Statistics statistics = new Statistics();

    public void loadTestCards() {
        System.out.println("Podaj nazwe pliku.");
        Scanner reader = new Scanner(System.in);
        String fileName = reader.nextLine();
        this.cardsSet.setAnswerCards(loader.loadCardSetFromCsv(fileName));
    }

    public void loadTestKey() {
        System.out.println("Podaj nazwe pliku.");
        Scanner reader = new Scanner(System.in);
        String fileName = reader.nextLine();
        this.cardsSet.setKey(loader.loadKeyFromCsv(fileName));
    }

    public void failedTests() {
        statistics.numberOfFailedTests(this.cardsSet);
    }

    public void histogram() {
        statistics.histogram(this.cardsSet);
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            System.out.println("1. Wczytaj odpowiedzi do testu.");
            System.out.println("2. Wczytaj klucz do testu.");
            System.out.println("3. Pokaz liczbe nie zaliczonych prac.");
            System.out.println("4. Wyswietl histogram zdobytych punktow.");
            System.out.println("0. Zakoncz");
            Scanner reader = new Scanner(System.in);
            char x = reader.next().charAt(0);
            switch (x) {
                case '1':
                    main.loadTestCards();
                    break;
                case '2':
                    main.loadTestKey();
                    break;
                case '3':
                    main.failedTests();
                    break;
                case '4':
                    main.histogram();
                    break;
                case '0':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji");
                    break;

            }
        }

    }

}
