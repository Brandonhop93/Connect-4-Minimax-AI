/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

import java.util.Scanner;

public class Player_Human extends Player
{
    //Human player input.
    private Scanner scanner;

    /** Class Constructor. */
    Player_Human(int player)
    {
        //Base constructor.
        super(player);

        //Initialise variables.
        this.scanner = new Scanner(System.in);
    }

    /** Class Constructor. */
    Player_Human(int player, Game game)
    {
        //Base constructor.
        super(player, game);

        //Initialise variables.
        this.scanner = new Scanner(System.in);
    }

    public int takeTurn()
    {
        //Console input.
        int insert = Integer.parseInt(scanner.next());

        //Insert at human decided position.
        game.insert(insert, player);

        //Return move made.
        return insert;
    }
}
