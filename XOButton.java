import javax.swing.JButton;

@SuppressWarnings("serial")
public class XOButton extends JButton {
    private Mark xOrO;
    private final int row;
    private final int column;

    public XOButton(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        setXOrO(Mark.EMPTY);
    }

    public int getRow() { return row; }
    public int getColumn() { return column; }

    public void setXOrO(Mark xOrO) {
        super.setText(xOrO.toString());
        this.xOrO = xOrO;
    }
    public String getXOrO() {
        return xOrO.toString();
    }

}
