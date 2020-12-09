package cpsc2150.extendedTicTacToe;


/**
 * @Invariants
 * the board can be accessed from rows: 0 - (row-1) and columns: 0 - (column - 1)
 * markers must be a valid character
 * an empty position must be filled with ' ' character
 *
 * @Constraints
 * 3 <= row <= 100
 * 3 <= col <= 100
 * 3 <= numToWin <= 25
 */
public class GameBoard extends AbsGameBoard implements IGameBoard
{
    private int row;
    private int col;
    private int numToWin;
    private char gameBoard[][];



    /**
     * Constructor
     * @pre none
     * @post initializes GameBoard object
     */
    public GameBoard(int r, int c, int numWin)
    {
        row = r;
        col = c;
        numToWin = numWin;

        gameBoard = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
    }


    public void placeMarker(BoardPosition marker, char player)
    {
        gameBoard[marker.getRow()][marker.getColumn()] = player;
    }


    public char whatsAtPos(BoardPosition pos)
    {
        char temp = gameBoard[pos.getRow()][pos.getColumn()];
        return temp;
    }


    public int getNumRows()
    {
        return row;
    }


    public int getNumColumns()
    {
        return col;
    }


    public int getNumToWin()
    {
        return numToWin;
    }

}
