import java.awt.event.ActionEvent;

/**
 * TTTController(TicTacToeController) will handle the TTT game logic
 */
public class TTTController {
    private final XOButton[][] ticTacToeBoard;
    private boolean hasWon;
    private Mark activePlayer;
    private boolean isXTheActivePlayer;
    private int currentRow;
    private int currentColumn;
    TTTController(XOButton[][] board) {
        hasWon = false;
        activePlayer = Mark.X;
        isXTheActivePlayer = true;
        this.ticTacToeBoard = board;
    }

    public void setCurrentRow(int row) { currentRow = row; }
    public int getCurrentRow() { return currentRow; }

    public void setCurrentColumn(int column) { currentColumn = column; }
    public int getCurrentColumn() { return currentColumn; }

    private void handlePlayerTurns() {
        isXTheActivePlayer = !isXTheActivePlayer;
        activePlayer = isXTheActivePlayer ? Mark.X : Mark.O;
    }

    public void processBoardEvent(ActionEvent e) {
        XOButton btn = ((XOButton)e.getSource());
        if (btn.getText().equals("")) {
            btn.setXOrO(activePlayer);
            checkIsWinningMove(btn.getRow(), btn.getColumn());
            handlePlayerTurns();
        }
    }

    public void checkIsWinningMove(int startRow, int startColumn) {
        setCurrentRow(startRow);
        setCurrentColumn(startColumn);
        checkVerticalOrHorizontal(false);
        // return hasWon;
    }

    private void checkVerticalOrHorizontal(boolean checkingIsVertical) {
        boolean[] visited = {false, false, false};
        String currentButtonXOrOMark = ticTacToeBoard[currentRow][currentColumn].getXOrO();
        if (checkingIsVertical) {
            System.out.println("vertical check");
        } else {
            visited[getCurrentColumn()] = true;
            for (int column = 0; column < visited.length; column++) {
                if (visited[column]) { continue; }
                System.out.printf("%n column = %d, currentRow = %d", column, getCurrentRow());
                String str1 = ticTacToeBoard[getCurrentRow()][column].getXOrO();
                boolean testbool = str1.equals(currentButtonXOrOMark);
                if (!testbool) {
                    return;
                }
            }
            hasWon = true;
            System.out.printf("%n horizontal win, hasWon = %b", hasWon);
        }
    }

}