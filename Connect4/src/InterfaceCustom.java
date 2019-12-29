/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

import java.util.LinkedList;

public class InterfaceCustom
{
    public static void main(String[] args)
    {
        //Player to play.
        Player p1 = new Player_Human(1);
        Player p2 = new Player_ABMinimax(2, 8);

        //Alternative player types.
        //Player p2 = new Player_Minimax(2, 8);
        //Player p2 = new Player_Human(2);
        //Player p2 = new Player_Random(2);

        //Compete one on one.
        compete(p1, p2);

        //Run benchmark.
        //benchmarkCompete(100, true, false, new Player_Random(1), new Player_ABMinimax(2, 10));
    }

    /** Compete one one one between two players. */
    private static void compete(Player p1, Player p2)
    {
        //Game to play.
        Game game = new Game();

        //Set player games.
        p1.setGame(game);
        p2.setGame(game);

        //Print empty board.
        game.printBoard();

        //Player turn.
        int turn = 1;

        while (!game.isGameEnded())
        {
            if (turn == 1)
            {
                System.out.println("Evaluation for board: " + game.evaluateBoard() + "\n");
                System.out.println("Player X turn:");
                p1.takeTurn();
                turn++;
            }

            else
            {
                System.out.println("Evaluation for board: " + game.evaluateBoard() + "\n");
                System.out.println("Player O turn:");
                p2.takeTurn();

                turn--;
            }

            //Thread.sleep(1000);
            game.printBoard();
        }

        if (turn == 1)
        {
            System.out.println("Evaluation for board: " + game.evaluateBoard() + "\n");
            System.out.println("Player O has won!");
        }

        else
        {
            System.out.println("Evaluation for board: " + game.evaluateBoard() + "\n");
            System.out.println("Player X has won!");
        }
    }

    /** Run a benchmark between two engines and return statistics. */
    private static void benchmarkCompete(int iterations, boolean printGameplay, boolean printRecord, Player p1, Player p2)
    {
        System.out.println("Computing...");

        //String replay.
        LinkedList<LinkedList<String>> replayList = new LinkedList<>();

        //Game to play.
        Game game = new Game();

        //Players to compete.
        Player[] players = new Player[]{p1, p2};

        //Set player games.
        players[0].setGame(game);
        players[1].setGame(game);

        //Score tracking.
        int p1_Wins = 0;
        int p2_Wins = 0;
        int draws = 0;

        LinkedList<Integer> p1_Times = new LinkedList<>();
        LinkedList<Integer> p2_Times = new LinkedList<>();

        //Compete until iterations reached.
        for (int i = 0; i < iterations; i++)
        {
            if (printRecord)
            {
                //Add new replay.
                replayList.addLast(new LinkedList<>());
            }

            //Console printing.
            System.out.println("Computing game: " + i + ".");

            //Restart the game.
            game.initialise();

            if (printGameplay)
            {
                //Print empty board.
                game.printBoard();
            }

            if (printRecord)
            {
                //Recording.
                replayList.getLast().addLast("\n//////////////REPLAY (" + replayList.size() + ") //////////////\n\n" + game.toString());
            }

            //Player turn.
            int turn = 1;

            while (!game.isGameEnded())
            {
                if (turn == 1)
                {
                    if (printGameplay)
                    {
                        //Console printing.
                        System.out.println("Player X turn:");
                    }

                    if (printRecord)
                    {
                        //Recording.
                        replayList.getLast().addLast("Evaluation for board: " + game.evaluateBoard() + "\n\n");
                        replayList.getLast().addLast("Player X turn:\n");
                    }


                    //Time metrics.
                    long start = System.currentTimeMillis();

                    players[0].takeTurn();

                    //Time metrics.
                    p1_Times.add((int)(System.currentTimeMillis() - start));

                    turn++;
                }

                else
                {
                    if (printGameplay)
                    {
                        //Console printing.
                        System.out.println("Player O turn:");
                    }

                    if (printRecord)
                    {
                        //Recording.
                        replayList.getLast().addLast("Evaluation for board: " + game.evaluateBoard() + "\n\n");
                        replayList.getLast().addLast("Player O turn:\n");
                    }

                    //Time metrics.
                    long start = System.currentTimeMillis();

                    players[1].takeTurn();

                    //Time metrics.
                    p2_Times.add((int)(System.currentTimeMillis() - start));

                    turn--;
                }

                if (printGameplay)
                {
                    System.out.print("\n");
                    game.printBoard();
                }

                if (printRecord)
                {
                    //Recording.
                    replayList.getLast().addLast(game.toString());
                }
            }

            //Recording.
            int winner = 0;

            if (game.totalInserts() == 42)
            {
                draws++;
            }

            else
            {
                if (turn == 1)
                {
                    if (printGameplay)
                    {
                        //Console printing.
                        System.out.println("Player O has won!\n");
                    }

                    if (printRecord)
                    {
                        //Recording.
                        replayList.getLast().addLast("Evaluation for board: " + game.evaluateBoard() + "\n\n");
                        replayList.getLast().addLast("Player O has won!\n");
                        winner = 2;
                    }

                    p2_Wins++;
                }

                else
                {
                    if (printGameplay)
                    {
                        //Console printing.
                        System.out.println("Player X has won!\n");
                    }

                    if (printRecord)
                    {
                        //Recording.
                        replayList.getLast().addLast("Evaluation for board: " + game.evaluateBoard() + "\n\n");
                        replayList.getLast().addLast("Player X has won!\n");
                        winner = 1;
                    }

                    p1_Wins++;
                }
            }

            if (printRecord)
            {
                replayList.getLast().addLast("\n//////////////////////////////////\n");
            }

            if (winner == 2)
            {
                replayList.removeLast();
            }
        }

        //Decimal formatter.
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");

        //Console printing.
        System.out.println("Player X has won: " + p1_Wins + " games - " + decimalFormat.format(((double) p1_Wins / ((double)iterations) * 100)) + "%");
        System.out.println("Player O has won: " + p2_Wins + " games - " + decimalFormat.format(((double) p2_Wins / ((double)iterations) * 100)) + "%");
        System.out.println("Draws: " + draws + " - " + decimalFormat.format(((double)draws / ((double)iterations) * 100)) + "%");

        System.out.println("\nCalculating move times...\n");

        long p1_AverageTime = 0;
        long p2_AverageTime = 0;

        for (int i = 0; i < p1_Times.size(); i++)
        {
            p1_AverageTime += p1_Times.get(i);
        }

        for (int i = 0; i < p2_Times.size(); i++)
        {
            p2_AverageTime += p2_Times.get(i);
        }

        p1_AverageTime /= p1_Times.size();
        p2_AverageTime /= p2_Times.size();

        System.out.println("Average X move time: " + p1_AverageTime + "ms");
        System.out.println("Average O move time: " + p2_AverageTime + "ms");
        System.out.print("\n");

        if (printRecord)
        {
            //Console printing.
            for (int i = 0; i < replayList.size(); i++)
            {
                for (int o = 0; o < replayList.get(i).size() ; o++)
                {
                    System.out.print(replayList.get(i).get(o));
                }
            }
        }
    }
}