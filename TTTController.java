import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

/**
 * TTTController(TicTacToeController) will handle the TTT game logic
 */
public class TTTController {
    private final XOButton[][] ticTacToeBoard;
    private boolean hasWon;
    private int moveCounter;
    private Mark activePlayer;
    private boolean isXTheActivePlayer;
    private int currentRow;
    private int currentColumn;
    TTTController(XOButton[][] board) {
        initializeGameStateValues();
        this.ticTacToeBoard = board;
    }

    private void initializeGameStateValues() {
        hasWon = false;
        moveCounter = 0;
        activePlayer = Mark.X;
        isXTheActivePlayer = true;
    }

    public void setHasWon(boolean hasWon) { this.hasWon = hasWon; }
    public boolean getHasWon() { return hasWon; }

    public void setActivePlayer(Mark mark) { this.activePlayer = mark; }
    public Mark getActivePlayer() { return activePlayer; }

    public void setIsXTheActivePLayer(boolean isXTheActivePlayer) { this.isXTheActivePlayer = isXTheActivePlayer; }
    public boolean getIsXTheActivePLayer() { return isXTheActivePlayer; }

    public void setCurrentRow(int row) { currentRow = row; }
    public int getCurrentRow() { return currentRow; }

    public void setCurrentColumn(int column) { currentColumn = column; }
    public int getCurrentColumn() { return currentColumn; }

    /**
     * Moves game along by updating the current player turn state.
     */
    private void handlePlayerTurns() {
        setIsXTheActivePLayer(!getIsXTheActivePLayer());
        setActivePlayer(getIsXTheActivePLayer() ? Mark.X : Mark.O);
    }

    private boolean isMarkEqualToActivePlayerMark(int row,int column) {
        return getActivePlayer().toString().equals(ticTacToeBoard[row][column].getXOrO());
    }

    /** 
     * Handles player board events and checks for a winning move.
    */
    public void processBoardEvent(ActionEvent e) {
        XOButton btn = ((XOButton)e.getSource());
        if (btn.getXOrO().equals("")) {
            moveCounter++;
            btn.setXOrO(getActivePlayer());
            // there can only be a win when a player has 3 in a row thus the earliest
            // a win can happen is on the five turn. No need to check for wins if it is not
            // possible for there to be a win. This makes the program more efficient and saves computing power.
            if (moveCounter > 4) {
                checkIsWinningMove(btn.getRow(), btn.getColumn());
                if (getHasWon() || moveCounter == ticTacToeBoard.length * ticTacToeBoard[0].length) {
                    String windowGameOverMessage = getHasWon() ? "Player win " + activePlayer : "Draw";
                    JOptionPane.showMessageDialog(null, windowGameOverMessage, "Game over!", JOptionPane.INFORMATION_MESSAGE);
                    resetGame();
                    return;
                }
            }
            handlePlayerTurns();
        }
    }

    /**
     * Relative to where the Mark was set, will check for possible wins along the row, column and diagonals. 
     * @param startRow the row the player mark is located in
     * @param startColumn the column the player mark is located in
     */
    public void checkIsWinningMove(int startRow, int startColumn) {
        setCurrentRow(startRow);
        setCurrentColumn(startColumn);
        checkVerticalAndHorizontal();
        if (getHasWon()) { return; }
        // formula to determine if the placed Mark can cause a diagonal win
        // odd numbers can not win via diagonals
        if ((getCurrentRow() + getCurrentColumn()) % 2 == 0) {
            checkDiagonal();
        }
    }

    /**
     * helper function for checkVerticalAndHorizontal().
     * cleans up the if statement to make it easier to read code.
     * @param direction the direction along a row or a column
     * @param checkColumn determine if the program is check along a row or column
     * @return based on checkColumn, returns if the player's Mark is the same as the one that is getting compared.
     */
    private boolean checkDirectionWithPivot(int direction, boolean checkColumn) {
        return checkColumn ? isMarkEqualToActivePlayerMark(direction, getCurrentColumn()) : isMarkEqualToActivePlayerMark(getCurrentRow(), direction);
    }

    /**
     * Handles checking if there is a win relative to the button pressed in the vertical and horizontal direction.
     * First while loop will first check if there is a win in the row the player has marked.
     * Then, it will check the column relative to the mark placed by the player for a win.
     */
    private void checkVerticalAndHorizontal() {
        int points = 1;
        int lengthColumnsRows = ticTacToeBoard.length;
        int pivot = getCurrentColumn();
        boolean checkColumn = false;
        int checkCount = 0;
        while (checkCount < 2 && !getHasWon()) {
            for (int i = 0; i < lengthColumnsRows; i++) {
                if (i != pivot) {
                    if (checkDirectionWithPivot(i, checkColumn)) {
                        points++;
                    } else {
                        points = 1;
                        break;
                    }
                }
            }
            setHasWon(points == 3);
            pivot = getCurrentRow();
            checkColumn = true;
            checkCount++;
        }
    }

    // To do: find a way to clean up checkDiagonal code
    // private void onCheckDiagonal() {
    //     int mod = 0;
    //     if (getCurrentRow() == getCurrentColumn()) {
    //         checkDiagonal(mod);
    //     }
    //     if ((getCurrentRow() != getCurrentColumn()) || (getCurrentRow() % 2 != 0)) {
    //         mod = 3;
    //         checkDiagonal(mod);
    //     }
    // }

    /**
     * To Do: clean up code
     * Will use the Mark placed by a player as a pivot to check for possible diagonal wins.
     * If the placed mark is in the middle of the board then it will check both diagonals for a win.
     */
    private void checkDiagonal() {
        int points = 1;
        int lengthColumnsRows = ticTacToeBoard.length;
        int pivot = getCurrentRow();
        // checks top left to bottom right diagonal of the board for win
        if (getCurrentRow() == getCurrentColumn()) {
            for (int i = 0; i < lengthColumnsRows; i++) {
                // no need to check the player placed mark since it implied
                if (i != pivot) {
                    if (isMarkEqualToActivePlayerMark(i, i)) {
                        points++;
                    } else {
                        points = 1;
                        // no need to check further since 3 of the same are needed for a win
                        break;
                    }
                }
            }
        }
        setHasWon(points == 3);
        if (getHasWon()) {
            return;
        }
        // checks top right to bottom left diagonal of the board for win
        // Middle can win via this diagonal too thus check for win
        if ((getCurrentRow() != getCurrentColumn()) || (getCurrentRow() % 2 != 0)) {
            int column = 2;
            for (int i = 0; i < lengthColumnsRows; i++) {
                if (i != pivot) {
                    // First argument climbs up while the second climbs down
                    if (isMarkEqualToActivePlayerMark(i, column - i)) {
                        points++;
                    } else {
                        break;
                    }
                }
            }
        }
        setHasWon(points == 3);
    }

    private void resetGame() {
        initializeGameStateValues();
        initializeBoardButtons();
    }

    /**
     * sets board button's Mark state to default values.
     */
    private void initializeBoardButtons() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                ticTacToeBoard[row][column].setXOrO(Mark.EMPTY);
            }
        }
    }
}