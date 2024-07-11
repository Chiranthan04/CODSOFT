import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private ArrayList<Question> questions;
    private int score;
    private int questionIndex;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        questionIndex = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (questionIndex < questions.size()) {
            displayQuestion(questions.get(questionIndex));
            boolean answered = waitForAnswer(scanner);
            if (!answered) {
                System.out.println("Time's up!");
            }
            questionIndex++;
        }
        displayResults();
        scanner.close();
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((char)('A' + i) + ": " + options[i]);
        }
    }

    private boolean waitForAnswer(Scanner scanner) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nYou ran out of time for this question.");
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
            }
        };

        timer.schedule(task, 10000); // 10 seconds timer

        System.out.print("Your answer: ");
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < 10000 && !scanner.hasNext()) {
            // wait for input or timeout
        }
        timer.cancel();

        if (scanner.hasNext()) {
            char answer = scanner.next().toUpperCase().charAt(0);
            if (questions.get(questionIndex).isCorrect(answer)) {
                score++;
            }
            return true;
        }
        return false;
    }

    private void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.getQuestion());
            System.out.println("Correct Answer: " + question.getCorrectAnswer());
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.addQuestion(new Question(
                "What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"},
                'C'
        ));
        quiz.addQuestion(new Question(
                "What is the largest planet in our solar system?",
                new String[]{"Earth", "Jupiter", "Mars", "Saturn"},
                'B'
        ));
        quiz.addQuestion(new Question(
                "Who wrote 'To Kill a Mockingbird'?",
                new String[]{"Harper Lee", "Mark Twain", "J.K. Rowling", "Jane Austen"},
                'A'
        ));
        quiz.start();
    }
}
