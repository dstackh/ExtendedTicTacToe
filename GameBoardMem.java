package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Invariant ( the board can be accessed from rows 0-(rows-1) and columns 0-(columns-1)
 *              markers must be a valid character
 *              an empty position must be filled with ' ' character
 * @Constraints:
 *              3 <= rows <= 100
 *              3 <= columns <= 100
 *              3 <= numToWin <= 25
 *
 */
public class GameBoardMem extends AbsGameBoard
{
    private Map<Character, List<BoardPosition>> gameBoard;
    int row;
    int col;
    int numToWin;

    /**
     * @pre [ r is a valid int row 3<=r<=100
     *      c is a valid int column 3<= c <=100
     *      numWin is a valid int for the number in a row to win must be 3<=numWin<=25
     * @param r integer that represents the row for the game
     * @param c integer that is used for the column the player has selected
     * @param numWin an integer that is used for the number of tokens in a row to win
     * @post
     */
    public GameBoardMem(int r, int c, int numWin)
    {
        row = r;
        col = c;
        numToWin = numWin;
        gameBoard = new HashMap<Character, List<BoardPosition>>();
    }


    public void placeMarker(BoardPosition marker, char player)
    {
        //if key isn't already in the map
        if(!gameBoard.containsKey(player))
        {
            List<BoardPosition> tempList = new ArrayList<BoardPosition>();
            tempList.add(marker);
            gameBoard.put(player, tempList);
        }
        else
        {
            //add to existing list
            gameBoard.get(player).add(marker);
        }
    }


    public char whatsAtPos(BoardPosition pos)   //gotta search through each key's list till you find it
    {
        char tempCh = ' ';
        for(char target : gameBoard.keySet())
        {
            if(gameBoard.get(target).contains(pos))
            {
                return target;
            }
        }
        return tempCh;
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)    //go to key and then search the list
    {
        if(gameBoard.get(player).contains(pos))
        {
            return true;
        }
        return false;
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
