package cpsc2150.extendedTicTacToe;


/**
 * A 2-D Gameboard of char data type
 * The Gameboard is bounded by maxRow being max rows and maxCol being max columns
 *
 * Initialization ensure: Depending on the implementation method chosen
 *                          Option one is a 2-D char array will be filled with blank spaces (' ')
 *                          Option two is a Map<Character, List<BoardPosition>> variable will initialize empty
 * Defines: col is the column which the last token was placed
 *          row is the row which the last token was placed
 *          numToWin is the number of tokens in a row needed
 * Constraints: 3 <= row <= 100
 *              3 <= col <= 100
 *              3 <= numToWin <=25
 */
public interface IGameBoard
{
    public static final int MIN_NUM_TO_WIN = 3;
    public static final int MIN_ROWS = 3;
    public static final int MIN_COLS = 3;

    /**
     * @pre [the position selected in the pos object must have valid coordinates]
     * @param pos a BoardPosition object that has coordinates of a position on the game board
     * @return returns true or false
     * @post will return true if the position if open and false otherwise
     */
    default boolean checkSpace(BoardPosition pos)
    {
        if(whatsAtPos(pos) == ' ')
        {
            return true;
        }
        return false;
    }


    /**
     * @pre [the position selected must be free for market to be placed, player must contain a char that represents one of the players]
     * @param marker a BoardPosition object that has the position of where the marker is to be placed
     * @param player a character variable that contains what letter should be placed in the target position
     * @post [marker is placed in the position designated by the BoardPosition object and the game board is updated]
     */
    public void placeMarker(BoardPosition marker, char player);


    /**
     * @pre [lastPos must contain a valid board position]
     * @param lastPos a BoardPosition object that has the position of the last placed marker
     * @return returns true or false
     * @post true if there is a win or false otherwise
     */
    default boolean checkForWinner(BoardPosition lastPos)
    {
        if(checkHorizontalWin(lastPos, this.whatsAtPos(lastPos)) == true)
        {
            return true;
        }
        else if(checkVerticalWin(lastPos, this.whatsAtPos(lastPos)) == true)
        {
            return true;
        }
        else if(checkDiagonalWin(lastPos, this.whatsAtPos(lastPos)) == true)
        {
            return true;
        }
        return false;
    }


    /**
     * @pre none
     * @return returns true or false
     * @post returns true if the game is a draw and false otherwise
     */
    default boolean checkForDraw()
    {

        for(int i = 0; i < this.getNumRows(); i++)
        {
            for(int j = 0; j < this.getNumColumns(); j++)
            {
                BoardPosition testPos = new BoardPosition(i, j);
                if(this.whatsAtPos(testPos) == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * @pre lastPos has 2 private int variables (row and column) 3 <= row < 100 and 3 <= column < 100, player must contain a char that represents one of the players
     * @param lastPos a BoardPosition object that has the position of the last placed marker
     * @param player a character variable that contains what letter should be placed in the target position
     * @return returns true or false
     * @post true if there is a horizontal win and false otherwise
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        int inARowCount = 0;

        for(int i = 0; i < this.getNumColumns(); i++)
        {
            BoardPosition pos = new BoardPosition(lastPos.getRow(), i);
            if(this.isPlayerAtPos(pos, player) == true)
            {
                inARowCount++;
            }
            if(inARowCount >= this.getNumToWin())
            {
                return true;
            }
            if(this.isPlayerAtPos(pos, player) == false)
            {
                inARowCount = 0;
            }
        }

        return false;
    }


    /**
     * @pre lastPos has 2 private int variables (row and column) 3 <= row < 100 and 3 <= column < 100, player must contain a char that represents one of the players
     * @param lastPos a BoardPosition object that has the position of the last placed marker
     * @param player a character variable that contains what letter should be placed in the target position
     * @return returns true or false
     * @post true if there is a vertical win, false otherwise
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int inARowCount = 0;

        for(int i = 0; i < this.getNumRows(); i++)
        {
            BoardPosition pos = new BoardPosition(i, lastPos.getColumn());
            if(this.isPlayerAtPos(pos, player) == true)
            {
                inARowCount++;
            }
            if(inARowCount >= this.getNumToWin())
            {
                return true;
            }
            if(this.isPlayerAtPos(pos, player) == false)
            {
                inARowCount = 0;
            }
        }

        return false;
    }


    /**
     * @pre lastPos has 2 private int variables (row and column) 3 <= row < 100 and 3 <= column < 100, player must contain a char that represents one of the players
     * @param lastPos a BoardPosition object that has the position of the last placed marker
     * @param player a character variable that contains what letter should be placed in the target position
     * @return returns true or false
     * @post true if there is a diagonal win or false otherwise
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        int inARowCount = 0;

        //check lower left
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition tempPos = new BoardPosition(lastPos.getRow()+i, lastPos.getColumn()-i);
            if(lastPos.getRow()+i >= this.getNumRows())
            {
                break;
            }
            if(lastPos.getColumn()-i < 0)
            {
                break;
            }
            if(this.isPlayerAtPos(tempPos, player) == true)
            {
                inARowCount++;
            }
        }
        //check upper right
        for(int j = 1; j <= this.getNumToWin(); j++)
        {
            if(lastPos.getRow()-j < 0)
            {
                break;
            }
            if(lastPos.getColumn()+j >= this.getNumColumns())
            {
                break;
            }
            BoardPosition tempPos = new BoardPosition(lastPos.getRow()-j, lastPos.getColumn()+j);
            if(this.isPlayerAtPos(tempPos, player))
            {
                inARowCount++;
            }
        }
        if(inARowCount >= this.getNumToWin()-1)
        {
            return true;
        }


        //reset to zero since we are switching which diagonal direction we are checking
        inARowCount = 0;


        //check lower right
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition tempPos = new BoardPosition(lastPos.getRow()+i, lastPos.getColumn()+i);
            if(lastPos.getRow()+i >= this.getNumRows())
            {
                break;
            }
            if(lastPos.getColumn()+i >= this.getNumColumns())
            {
                break;
            }
            if(this.isPlayerAtPos(tempPos, player))
            {
                inARowCount++;
            }
        }
        //check upper left
        for(int j = 1; j <= this.getNumToWin(); j++)
        {
            BoardPosition tempPos = new BoardPosition(lastPos.getRow()-j, lastPos.getColumn()-j);
            if(lastPos.getRow()-j < 0)
            {
                break;
            }
            if(lastPos.getColumn()-j < 0)
            {
                break;
            }
            if(this.isPlayerAtPos(tempPos, player))
            {
                inARowCount++;
            }
        }
        if(inARowCount >= this.getNumToWin()-1)
        {
            return true;
        }

        return false;
    }


    /**
     * @pre pos has 2 private int variables (row and column) 3 <= row < 100 and 3 <= column < 100
     * @param pos a BoardPosition object that contains coordinates for the target board position
     * @return returns a char
     * @post [returns the character that is found at the target board position, designated by pos]
     */
    public char whatsAtPos(BoardPosition pos);


    /**
     * @pre pos has 2 private int variables (row and column) 3 <= row < 100 and 3 <= column < 100, player must contain a char that represents one of the players
     * @param pos a BoardPosition object that contains coordinates for the target board position
     * @param player a character variables that contains a letter for one of the players
     * @return returns true or false
     * @post true if the character stored in player is at the coordinates in pos, false otherwise
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if(this.whatsAtPos(pos) == player)
        {
            return true;
        }
        return false;
    }



    /**
     * @pre there must be an initialized GameBoard object to use this method
     * @post returns the number of rows in the GameBoard
     * @return an integer representing the number of rows in the game board
     */
    public int getNumRows();


    /**
     * @pre there must be an initialized GameBoard object to use this method
     * @post returns the number of columns in the GameBoard
     * @return an integer representing the number of columns in the game board
     */
    public int getNumColumns();


    /**
     * @pre there must be an initialized GameBoard object to use this method
     * @post returns the number of tokens in a row needed to win the game
     * @return an integer representing the number of markers in a row needed to win
     */
    public int getNumToWin();

}
