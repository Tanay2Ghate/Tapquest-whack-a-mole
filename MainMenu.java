import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Game Hub");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel titleLabel = new JLabel("Select a Game to Play!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        JButton whackAMoleBtn = new JButton("Play Whack-a-Mole");
        whackAMoleBtn.setFont(new Font("Arial", Font.BOLD, 18));
        whackAMoleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WhackAMole game = new WhackAMole();
                game.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // So it doesn't close the MainMenu
                game.setVisible(true);
            }
        });
        add(whackAMoleBtn);

        JButton ticTacToeBtn = new JButton("Play Tic-Tac-Toe");
        ticTacToeBtn.setFont(new Font("Arial", Font.BOLD, 18));
        ticTacToeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicTacToe().setVisible(true);
            }
        });
        add(ticTacToeBtn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}
