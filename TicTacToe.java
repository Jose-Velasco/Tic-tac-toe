// Name: Jose Velasco
// Date: 1/26/2021
// Description: Tic tac toe game object that hold the main to initialize and start the game
// File Name: TicTacToe.java

// import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Container;
import java.awt.*;

public class TicTacToe {
    private JFrame mainFrame;
    private int height;
    private int width;
    private Board board;

    public TicTacToe(int width, int height) {
        int rowsAndColumns = 3;
        this.height = height;
        this.width = width;
        mainFrame = new JFrame("Tic Tac Toe!");
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        Container container = mainFrame.getContentPane();
        container.setLayout(new GridLayout(rowsAndColumns, rowsAndColumns));

        board = new Board(container, rowsAndColumns);
        // JButton btn = new XOButton();
        // btn.set
        // container.add(btn);

        mainFrame.setSize(this.width, this.height);
        mainFrame.setVisible(true);
    }

    public void onButtonClickEvent(ActionEvent e) {
        System.out.println(e.getSource());
    }
    public static void main(String[] args) {
        int height = 600;
        int width = 600;
        TicTacToe game = new TicTacToe(width, height);
        System.out.println("TicTacToe.java works");
        System.out.println(Mark.X);
    }
}
