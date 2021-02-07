/**
 * TTTController(TicTacToeController) will handle the TTT game logic
 */
public class TTTController {
    private final XOButton[][] ticTacToeBoard;
    private boolean hasWon;
    private int currentRow;
    private int currentColumn;
    TTTController(XOButton[][] board) {
        hasWon = false;
        this.ticTacToeBoard = board;
    }

    public void setCurrentRow(int row) { currentRow = row; }
    public int getCurrentRow() { return currentRow; }

    public void setCurrentColumn(int column) { currentColumn = column; }
    public int getCurrentColumn() { return currentColumn; }

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