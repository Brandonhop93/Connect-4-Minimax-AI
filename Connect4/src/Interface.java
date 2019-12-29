/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

import java.util.Scanner;

public class Interface
{
    public static void main(String[] args)
    {
        //Console input.
        Scanner scanner = new Scanner(System.in);

        //Coordinator message.
        String[] message;

        //AI player.
        Minimax minimax = new Minimax();

        //Connect 4 game.
        Game game = new Game();

        //Player value.
        int plrValue = 1;
        int oppValue = 2;

        //Time management.
        int totalDepth = 8;
        int iterations = 5;
        int[] times = new int[totalDepth + 1];

        //Pre-compute average depth time.
        for (int depth = 5; depth < times.length; depth++)
        {
            long averageTime = 0;

            for (int i = 0; i < iterations; i++)
            {
                //Start time.
                long start = System.currentTimeMillis();

                minimax.minimaxStartAlphaBeta(game, depth, 1);

                //Finish time.
                averageTime += System.currentTimeMillis() - start;
            }

            //Store average time.
            times[depth] = (int)((double) averageTime / (double) iterations);
        }

        loop: while (true)
        {
            //Split console input.
            message = scanner.nextLine().split(" ");

            switch (message[0])
            {
                //Input: name AI 0
                case "name":

                    //Assign minimising/maximising player.
                    if (Integer.parseInt(message[2]) == 0)
                    {
                        plrValue =  1;
                        oppValue =  2;
                    }

                    else
                    {
                        plrValue =  2;
                        oppValue =  1;
                    }

                    //Console output.
                    System.out.println("HASH_DADDY-c3290146");

                    break;

                //Input: position startpos 0123456
                case "position":

                    if (message.length == 3)
                    {
                        //Insert opponents move.
                        game.insert(Integer.parseInt(message[2].substring(message[2].length() - 1)), oppValue);
                    }

                    break;

                //Input: isready AI 0
                case "isready":

                    //Console output.
                    System.out.println("readyok");

                    break;

                //Input: go ftime 120000 stime 120000
                case "go":

                    //Time remaining from input.
                    int timeRemaining;

                    //Position to insert.
                    int insert;

                    if (plrValue == 1)
                    {
                        //Time remaining from input.
                        timeRemaining = Integer.parseInt(message[2]) / 2;
                    }

                    else
                    {
                        //Time remaining from input.
                        timeRemaining = Integer.parseInt(message[4]) / 2;
                    }

                    //Time management - Depth 8.
                    if (timeRemaining > times[totalDepth - 1])
                    {
                        //Find best board insert.
                        insert = minimax.minimaxStartAlphaBeta(game, 8, plrValue);
                    }

                    //Time management - Depth 7.
                    else if (timeRemaining > times[totalDepth - 2])
                    {
                        //Find best board insert.
                        insert = minimax.minimaxStartAlphaBeta(game, 7, plrValue);
                    }

                    //Time management - Depth 6.
                    else if (timeRemaining > times[totalDepth - 3])
                    {
                        //Find best board insert.
                        insert = minimax.minimaxStartAlphaBeta(game, 6, plrValue);
                    }

                    //Time management - Depth 5.
                    else
                    {
                        //Find best board insert.
                        insert = minimax.minimaxStartAlphaBeta(game, 5, plrValue);
                    }

                    //Insert piece.
                    game.insert(insert, plrValue);

                    //Console output.
                    System.out.println("bestmove " +  insert + " " + game.evaluateBoard());

                    break;

                //Input: perft x
                case "perft":

                    //Console output.
                    System.out.println("perft " + message[1] + " " + minimax.perf(game, Integer.parseInt(message[1]), plrValue));

                    break;

                case "quit":

                    //Close the input scanner.
                    scanner.close();

                    //Console output.
                    System.out.println("quitting");

                    break loop;
            }
        }
    }
}