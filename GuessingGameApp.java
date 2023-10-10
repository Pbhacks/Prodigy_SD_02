import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameApp {
    private int randomNumber;
    private int attempts;
    private JFrame frame;
    private JLabel instructionsLabel;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JButton submitButton;

    public GuessingGameApp() {
        frame = new JFrame("Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        instructionsLabel = new JLabel("Guess the number between 1 and 100:");
        guessField = new JTextField(10);
        feedbackLabel = new JLabel();
        attemptsLabel = new JLabel("Attempts: 0");
        submitButton = new JButton("Submit");

        frame.add(instructionsLabel);
        frame.add(guessField);
        frame.add(feedbackLabel);
        frame.add(attemptsLabel);
        frame.add(submitButton);

        // Generate a random number
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;

        // Handle user input and game logic
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == randomNumber) {
                feedbackLabel.setText("Congratulations! You guessed the correct number.");
                showResultsDialog(1, attempts); // 1 for correct guess
                submitButton.setEnabled(false); // Disable further attempts
            } else if (guess < randomNumber) {
                feedbackLabel.setText("Too low. Try again.");
            } else {
                feedbackLabel.setText("Too high. Try again.");
            }

            attemptsLabel.setText("Attempts: " + attempts);
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    private void showResultsDialog(int correctAttempts, int totalAttempts) {
        double percentage = ((double) correctAttempts / totalAttempts) * 100;
        String message = String.format("You guessed correctly %.2f%% of the time.", percentage);
        JOptionPane.showMessageDialog(frame, message, "Game Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessingGameApp();
            }
        });
    }
}
