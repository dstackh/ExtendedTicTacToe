package cpsc2150.extendedTicTacToe;


/**
 * @Constraints
 * 2 <= row < 100
 * 2 <= column < 100
 */
public class BoardPosition
{
    private int row;
    private int col;


    /**
     * Constructor
     * @pre none
     * @param r the selected row
     * @param c the selected column
     * @post each position in the game board has a specific BoardPosition object
     */
    public BoardPosition(int r, int c)
    {
        row = r;
        col = c;
    }


    /**
     * @pre none
     * @return returns the row of the game board
     * @post returns the value of the row as an int
     */
    public int getRow()
    {
        return row;
    }


    /**
     * @pre none
     * @return returns the column of the game board
     * @post returns the value of the column as an int
     */
    public int getColumn()
    {
        return col;
    }


    /**
     * Overrides the toString function in the object class
     * @return returns a string
     * @post string of the board position
     */
    @Override
    public String toString()
    {
        String tempStr = getRow() + ", " + getColumn();
        return tempStr;
    }


    /**
     * Overrides the equals function in the object class
     * @pre any valid object
     * @param o object to compare with
     * @return returns true if the objects are of the same type and false otherwise
     * @post returns the boolean value of two objects depending on if they have the same private data
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof BoardPosition))
        {
            return false;
        }
        BoardPosition temp = (BoardPosition) o;
        if(this.getRow() == temp.getRow())
        {
            if(this.getColumn() == temp.getColumn())
            {
                return true;
            }
        }
        return false;
    }
}
