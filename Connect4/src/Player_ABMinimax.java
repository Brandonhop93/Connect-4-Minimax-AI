/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

public class Player_ABMinimax extends Player
{
    //Minimax algorithm.
    private Minimax minimax;

    //Search depth.
    private int depth;

    /** Class Constructor. */
    public Player_ABMinimax(int player, int depth)
    {
        //Base constructor.
        super(player);

        //Initialise variables.
        this.minimax = new Minimax();
        this.depth = depth;
    }

    /** Class Constructor. */
    public Player_ABMinimax(int player, int depth, Game game)
    {
        //Base constructor.
        super(player, game);

        //Initialise variables.
        this.minimax = new Minimax();
        this.depth = depth;
    }

    /** AB Minimax computed move. */
    public int takeTurn()
    {
        int insert = minimax.minimaxStartAlphaBeta(game, depth, player);

        if (game.canInsert(insert))
        {
            //Insert at calculated position.
            game.insert(insert, player);

            //Return move made.
            return insert;
        }

        else
        {
            throw new IllegalStateException("Player " + player + " cannot make move, board full.");
        }
    }
}
