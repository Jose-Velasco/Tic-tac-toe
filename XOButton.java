// Name: Jose Velasco
// Date: Start - 1/26/2021
// Description: inherits from JButton to define a custom Tic tac toe button
// File Name: XOButton.java

import javax.swing.JButton;

// @SuppressWarnings stops annoying IDE warning message
@SuppressWarnings("serial")
public class XOButton extends JButton {
    private Mark xOrO;
    private final int row;
    private final int column;

    /**
     * initializes XOButton and sets its location in the 2d array
     * @param row row the button is in
     * @param column column the button is in
     */
    public XOButton(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        setXOrO(Mark.EMPTY);
    }

    public int getRow() { return row; }
    public int getColumn() { return column; }

    /**
     * sets button's text to player's Mark and updates the buttons Mark state.
     * @param xOrO Mark to update button's Mark state to
     */
    public void setXOrO(Mark xOrO) {
        super.setText(xOrO.toString());
        this.xOrO = xOrO;
    }
    public String getXOrO() {
        return xOrO.toString();
    }
}
