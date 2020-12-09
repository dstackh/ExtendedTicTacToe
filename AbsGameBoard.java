package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard
{

    /**
     * Creates a string representation of the current game board
     * @pre needs an initialized IGameBoard object
     * @return returns a string
     * @post returns a string representation of the current state of the game board
     */
    @Override
    public String toString()
    {
        BoardPosition tempPos;
        String tempStr = "    ";

        int tempCount = 0;
        tempStr = tempStr + tempCount;
        tempCount++;

        while(tempCount < this.getNumColumns())
        {
            if(tempCount < 10)
            {
                tempStr = tempStr + "| " + tempCount;
            }
            else
            {
                tempStr = tempStr + "|" + tempCount;
            }
            tempCount++;
        }
        tempStr = tempStr + "|\n";

        tempCount = 0;
        while(tempCount < this.getNumRows())
        {
            if(tempCount < 10)
            {
                tempStr = tempStr + " " + tempCount + "|";
            }
            else
            {
                tempStr = tempStr + tempCount + "|";
            }
            for(int i = 0; i < this.getNumColumns(); i++)
            {
                tempPos = new BoardPosition(tempCount, i);
                tempStr = tempStr + this.whatsAtPos(tempPos) + " |";
            }

            tempCount++;
            tempStr = tempStr + "\n";
        }

        return tempStr;
    }
}
