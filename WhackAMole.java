import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WhackAMole extends JFrame {
    private JButton[] holes = new JButton[9];
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private int score = 0;
    private int timeLeft = 30; // 30 seconds
    private Timer gameTimer;
    private Timer moleTimer;
    private int currentMoleIndex = -1;
    private Random random = new Random();

    public WhackAMole() {
        setTitle("Whack-a-Mole");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel for Score and Time
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(scoreLabel);

        timeLabel = new JLabel("Time: " + timeLeft);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(timeLabel);

        add(topPanel, BorderLayout.NORTH);

        // Grid Panel for Holes
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3, 5, 5));

        for (int i = 0; i < 9; i++) {
            holes[i] = new JButton();
            holes[i].setFont(new Font("Arial", Font.BOLD, 40));
            holes[i].setFocusPainted(false);
            holes[i].setBackground(Color.LIGHT_GRAY);
            final int index = i;
            
            holes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index == currentMoleIndex) {
                        score++;
                        scoreLabel.setText("Score: " + score);
                        holes[index].setText("");
                        holes[index].setBackground(Color.LIGHT_GRAY);
                        currentMoleIndex = -1;
                    }
                }
            });
            gridPanel.add(holes[i]);
        }
        
        add(gridPanel, BorderLayout.CENTER);

        // Bottom Panel for Start Button
        JPanel bottomPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        bottomPanel.add(startButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void startGame() {
        score = 0;
        timeLeft = 30;
        scoreLabel.setText("Score: " + score);
        timeLabel.setText("Time: " + timeLeft);
        currentMoleIndex = -1;
        
        for (JButton hole : holes) {
            hole.setText("");
            hole.setBackground(Color.LIGHT_GRAY);
        }

        if (gameTimer != null) gameTimer.stop();
        if (moleTimer != null) moleTimer.stop();

        // Timer for countdown
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timeLabel.setText("Time: " + timeLeft);
                if (timeLeft <= 0) {
                    endGame();
                }
            }
        });
        gameTimer.start();

        // Timer for mole appearance
        moleTimer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear previous mole
                if (currentMoleIndex != -1) {
                    holes[currentMoleIndex].setText("");
                    holes[currentMoleIndex].setBackground(Color.LIGHT_GRAY);
                }

                if (timeLeft > 0) {
                    // Show new mole
                    currentMoleIndex = random.nextInt(9);
                    holes[currentMoleIndex].setText("M");
                    holes[currentMoleIndex].setBackground(Color.ORANGE);
                }
            }
        });
        moleTimer.start();
    }

    private void endGame() {
        gameTimer.stop();
        moleTimer.stop();
        if (currentMoleIndex != -1) {
            holes[currentMoleIndex].setText("");
            holes[currentMoleIndex].setBackground(Color.LIGHT_GRAY);
        }
        JOptionPane.showMessageDialog(this, "Game Over!\nYour Score: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WhackAMole().setVisible(true);
            }
        });
    }
}
