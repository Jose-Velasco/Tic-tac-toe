// Name: Jose Velasco
// Date: Start - 1/26/2021
// Description: Tic tac toe game object that hold the main to initialize, start the game,
//              and to render the view container
// File Name: TicTacToe.java

import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Container;
import java.awt.*;

public class TicTacToe {
    private JFrame mainFrame;
    private int height;
    private int width;

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

        new Board(container, rowsAndColumns);

        mainFrame.setSize(this.width, this.height);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        int height = 600;
        int width = 600;
        new TicTacToe(width, height);
    }
}
