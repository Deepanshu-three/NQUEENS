import javax.swing.*;
import java.awt.*;

public class NQueen {

    final static int N = 4; // Define the size of the chessboard
    static JLabel[][] labels = new JLabel[N][N]; // Array to hold JLabels representing each cell
    static int[][] board = new int[N][N]; // 2D array to represent the chessboard

    // Method to print the solution matrix (not used in visualization)
    static void printSolution() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.printf("\n");
        }
    }

    // Method to check if a queen can be placed safely at board[row][col]
    static boolean isSafe(int row, int col) {
        try {
            Thread.sleep(200); // Delay for visualization (optional)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if there is a queen in the same row to the left
        for (int i = 0; i < col; ++i)
            if (board[row][i] == 1)
                return false;

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; --i, --j)
            if (board[i][j] == 1)
                return false;

        // Check lower diagonal on left side
        for (int i = row, j = col; i < N && j >= 0; ++i, --j)
            if (board[i][j] == 1)
                return false;

        return true; // Return true if it's safe to place a queen at board[row][col]
    }

    // Recursive function to solve N Queen problem starting from column col
    static boolean solveNQueen(int col) {
        if (col >= N) // If all queens are placed, return true
            return true;

        // Try placing a queen in each row of the current column col
        for (int i = 0; i < N; ++i) {
            try {
                Thread.sleep(100); // Delay for visualization (optional)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isSafe(i, col)) { // Check if it's safe to place a queen at board[i][col]
                board[i][col] = 1; // Place the queen
                labels[i][col].setBackground(Color.GREEN); // Visualize queen placement

                if (solveNQueen(col + 1)) // Recur to place queens in subsequent columns
                    return true;

                board[i][col] = 0; // Backtrack: Remove queen and backtrack
                labels[i][col].setBackground(Color.RED); // Visualize backtracking
            }
        }
        return false; // Return false if no solution exists for the current configuration
    }

    // Method to initialize Swing components and display the chessboard
    NQueen() {
        JFrame frame = new JFrame("N Queen Problem Visualizer"); // Create a new JFrame
        frame.setLayout(new GridLayout(N, N)); // Set layout to grid layout of size N x N
        frame.setSize(400, 400); // Set frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation

        // Initialize JLabels for each cell in the grid
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                labels[i][j] = new JLabel( "(" + i + "," + j + ")");
                labels[i][j] = new JLabel(""); // Create new JLabel with empty text
                labels[i][j].setOpaque(true); // Set opaque to true for background color
                labels[i][j].setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
                labels[i][j].setBackground(Color.BLUE); // Set initial background color to blue
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border for clarity
                frame.add(labels[i][j]); // Add JLabel to the JFrame
            }
        }

        frame.setVisible(true); // Make the JFrame visible
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NQueen(); // Initialize Swing components
        });

        solveNQueen(0); // Solve N Queen problem starting from column 0
    }
}
