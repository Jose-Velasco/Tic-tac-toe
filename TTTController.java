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
            btn.setXOrO(getActivePlayer());
            checkIsWinningMove(btn.getRow(), btn.getColumn());
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
    }

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
}