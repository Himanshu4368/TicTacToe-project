
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] board;
    private char player;
    private boolean gameOver;

    public static void main(String[] args) {
        new TicTacToe();
    }

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        buttons = new JButton[3][3];
        board = new char[3][3];
        player = 'X';
        gameOver = false;

        initializeBoard();
        initializeGUI();
    }

    private void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
    }

    private void initializeGUI() {
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                frame.add(buttons[row][col]);
            }
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameOver || board[row][col] != ' ') {
                JOptionPane.showMessageDialog(frame, "Invalid move. Try again!");
                return;
            }

            // Place the element
            board[row][col] = player;
            buttons[row][col].setText(String.valueOf(player));

            if (haveWon(player)) {
                JOptionPane.showMessageDialog(frame, "Player " + player + " has won!");
                gameOver = true;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(frame, "It's a tie!");
                gameOver = true;
            } else {
                // Switch player
                player = (player == 'X') ? 'O' : 'X';
            }
        }
    }

    public boolean haveWon(char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
