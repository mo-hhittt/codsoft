import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizGame {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public QuizGame() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
        timer = new Timer();
    }

    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    public void startGame() {
        System.out.println("Welcome to the Quiz Game!");
        for (QuizQuestion question : questions) {
            presentQuestion(question);
        }
        displayResult();
    }

    private void presentQuestion(QuizQuestion question) {
        System.out.println("\nQuestion: " + question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option (1-" + options.size() + "): ");

        int selectedOption = scanner.nextInt();
        if (selectedOption == question.getCorrectAnswerIndex() + 1) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was option " + (question.getCorrectAnswerIndex() + 1));
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz Complete!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        QuizGame quizGame = new QuizGame();

        // Add questions here
        quizGame.addQuestion(new QuizQuestion("What is the capital of France?",
                List.of("London", "Berlin", "Paris", "Madrid"), 2));
        quizGame.addQuestion(new QuizQuestion("Which planet is known as the Red Planet?",
                List.of("Mars", "Venus", "Jupiter", "Earth"), 0));
        quizGame.addQuestion(new QuizQuestion("What is the largest mammal in the world?",
                List.of("Elephant", "Giraffe", "Blue Whale", "Kangaroo"), 2));

        quizGame.startGame();
    }
}
