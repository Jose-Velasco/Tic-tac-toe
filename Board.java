import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;


public class Board {
    private XOButton[][] ticTacToeBoard;
    private Mark activePlayer;
    private boolean isXTheActivePlayer;
    private TTTController controller;
    public Board(Container mainFrameContainer, int rowsAndColumns) {
        activePlayer = Mark.X;
        isXTheActivePlayer = true;
        ticTacToeBoard = new XOButton[rowsAndColumns][rowsAndColumns];
        generateBoard(mainFrameContainer);
        controller = new TTTController(ticTacToeBoard);
    }

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
        XOButton btn = ((XOButton)e.getSource());
        // Container parentCont = btn.getParent();
        if (btn.getText().equals("")) {
            btn.setXOrO(activePlayer);
            controller.checkIsWinningMove(btn.getRow(), btn.getColumn());
            handlePlayerTurns();
        }
    }

    private void handlePlayerTurns() {
        isXTheActivePlayer = !isXTheActivePlayer;
        activePlayer = isXTheActivePlayer ? Mark.X : Mark.O;
    }
}
