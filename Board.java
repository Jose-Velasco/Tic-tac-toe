// Name: Jose Velasco
// Date: Start - 1/26/2021
// Description: handles the board view
// File Name: Board.java

import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;

public class Board {
    private XOButton[][] ticTacToeBoard;
    private TTTController controller;
    public Board(Container mainFrameContainer, int rowsAndColumns) {
        ticTacToeBoard = new XOButton[rowsAndColumns][rowsAndColumns];
        generateBoard(mainFrameContainer);
        controller = new TTTController(ticTacToeBoard);
    }

    /**
     * generates game board buttons and adds them to the container.
     * In addition, the button's font size, coordinates and actionLister will be assigned 
     * @param mainFrameContainer content pane container of JFrame
     */
    private void generateBoard(Container mainFrameContainer) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                int fontSize = 100;
                XOButton newButton = new XOButton(row, column);
                newButton.setFont(new Font(newButton.getFont().getName(), Font.BOLD, fontSize));
                newButton.addActionListener(
                    this::onActionPerformed
                );
                mainFrameContainer.add(newButton);
                ticTacToeBoard[row][column] = newButton;
            }
        }
    }

    private void onActionPerformed(ActionEvent e) {
        controller.processBoardEvent(e);
    }
}
