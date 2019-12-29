/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

import java.util.Random;

public class Player_Random extends Player
{
    /** Class Constructor. */
    public Player_Random(int player)
    {
        //Base constructor.
        super(player);
    }

    /** Class Constructor. */
    public Player_Random(int player, Game game)
    {
        //Base constructor.
        super(player, game);
    }

    /** Randomly computed move. */
    public int takeTurn()
    {
        //Random number between 0 - 6.
        int random = new Random().nextInt(7);

        //Check can insert at random position.
        while (!game.canInsert(random))
        {
            //Choose a new random number.
            random = new Random().nextInt(7);
        }

        //Insert at random position.
        game.insert(random, player);

        //Return move made.
        return random;
    }
}