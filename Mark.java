// Name: Jose Velasco
// Date: Start - 1/26/2021
// Description: creates the possible Marks that are placed in the tic tac toe board.
//              This enum helps guarantee the possible Marks when working with this project
// File Name: Mark.java

/**
 * In the game of TicTacToe there can only be X, O and when there is no Mark then an empty space
 */
public enum Mark {
    X("X"),
    O("O"),
    EMPTY("");

    private final String mark;
    Mark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() { return mark; }
}