/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

public abstract class Player
{
    //Maximising or minimising.
    protected int player;

    //Game to play.
    protected Game game;

    /** Class Constructor. */
    public Player(int player)
    {
        if (player == 1 || player == 2)
        {
            //Initialise player.
            this.player = player;
        }

        else
        {
            //Exception handling.
            throw new IllegalStateException("Player must be a value of 1 or 2.");
        }
    }

    /** Class Constructor. */
    public Player(int player, Game game)
    {
        if (player == 1 || player == 2)
        {
            //Initialise player.
            this.player = player;
        }

        else
        {
            //Exception handling.
            throw new IllegalStateException("Player must be a value of 1 or 2.");
        }

        //Initialise game.
        this.game = game;
    }

    /** Take player turn. */
    public abstract int takeTurn();

    /** Set player number. */
    public void setPlayer(int player)
    {
        this.player = player;
    }

    /** Get player number. */
    public int getPlayer()
    {
        return player;
    }

    /** Set game. */
    public void setGame(Game game)
    {
        this.game = game;
    }

    /** Get game. */
    public Game getGame()
    {
        return game;
    }
}