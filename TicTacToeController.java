package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController
{
    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    public static final int MAX_PLAYERS = 10;
    private char[] players = {'X', 'O', 'A', 'S', 'R', 'E', 'C', 'K', 'I', 'Z'};
    private int numPlayers;
    private int turn;
    private boolean gameOver;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @param np The number of players for the game
     * @post the controller will respond to actions on the view using the model.
     */
    TicTacToeController(IGameBoard model, TicTacToeView view, int np)
    {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
        turn = 0;
        gameOver = false;
    }

    /**
     *
     * @param row the row of the activated button
     * @param col the column of the activated button
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col)
    {
        BoardPosition pos = new BoardPosition(row, col);
        //when the game has been determined as a win or a tie, the following button click should start a new game
        //this if statement tests for that scenario
        if (gameOver == true)
        {
            this.newGame();
        }

        //If the position clicked is already taken
        if(!curGame.checkSpace(pos))
        {
            screen.setMessage("Position is taken, please try again.");
        }
        else //If the position clicked is not already taken
        {

            //sets the new marker
            screen.setMarker(row, col, players[turn]);
            curGame.placeMarker(pos, players[turn]);

            //checks for a draw
            if (curGame.checkForDraw())
            {
                screen.setMessage("Game has ended in a tie");
                gameOver = true;
            }

            //checks for a win
            if(curGame.checkForWinner(pos))
            {
                screen.setMessage("Player " + players[turn] + " is the winner");
                gameOver = true;
            }

            //Cycle to the next player
            turn++;
            if(turn >= numPlayers)
            {
                turn = 0;

            }

            //If the previous marker placed did not result in a win and wasn't already taken, prompt the next player that it is their turn
            if(!curGame.checkForWinner(pos))
            {
                screen.setMessage("It is " + players[turn] + "\'s turn.");
            }
        }
    }



    private void newGame()
    {
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}
