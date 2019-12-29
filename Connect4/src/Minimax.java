/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

public class Minimax
{
    /** Recursive perf function to count nodes. */
    public long perf(Game game, int depth, int player)
    {
        //Node count.
        long nodesExplored = 1;

        //Check for game finished.
        if (depth == 0 || game.isGameEnded())
        {
            return nodesExplored;
        }

        else
        {
            //Loop over all columns.
            for (int i = 0; i < 7; i++)
            {
                if (game.canInsert(i))
                {
                    //Insert game piece.
                    game.insert(i, player);

                    if (player == 1)
                    {
                        nodesExplored += perf(game, depth - 1, 2);
                    }

                    else
                    {
                        nodesExplored += perf(game, depth - 1, 1);
                    }

                    //Extract game piece.
                    game.extract(i);
                }
            }
        }

        //Return node count.
        return nodesExplored;
    }

    /** Helper/starter function for minimax algorithm. */
    public int minimaxStart(Game game, int depth, int player)
    {
        int bestIndex = 0;
        int bestValue;

        //Player 2 (Maximising)
        if (player == 2)
        {
            bestValue = Integer.MIN_VALUE;

            int index = 3;

            //Loop middle outwards.
            for (int i = 1; i < 8; i++)
            {
                if (game.canInsert(index))
                {
                    //Insert game piece.
                    game.insert(index, player);

                    int tempValue = minimax(game, depth - 1, 1);

                    //Extract game piece.
                    game.extract(index);

                    //Update to the larger value.
                    if (tempValue > bestValue)
                    {
                        bestValue = tempValue;
                        bestIndex = index;
                    }
                }

                if (index > 3)
                {
                    //Loop middle to left.
                    index -= i;
                }

                else
                {
                    //Loop middle to right.
                    index += i;
                }
            }
        }

        //Player 1 (Minimising)
        else
        {
            bestValue = Integer.MAX_VALUE;

            int index = 3;

            //Loop middle outwards.
            for (int i = 1; i < 8; i++)
            {
                if (game.canInsert(index))
                {
                    //Insert game piece.
                    game.insert(index, player);

                    int tempValue = minimax(game, depth - 1, 2);

                    //Extract game piece.
                    game.extract(index);

                    //Update to the larger value.
                    if (tempValue < bestValue)
                    {
                        bestValue = tempValue;
                        bestIndex = index;
                    }
                }

                if (index > 3)
                {
                    //Loop middle to left.
                    index -= i;
                }

                else
                {
                    //Loop middle to right.
                    index += i;
                }
            }
        }

        return bestIndex;
    }

    /** Recursive minimax algorithm. */
    private int minimax(Game game, int depth, int player)
    {
        //Check for game finished.
        if (depth == 0 || game.isGameEnded())
        {
            if (depth != 0)
            {
                //Return the closest node if a win is found.
                return game.evaluateBoard() * depth;
            }

            else
            {
                return game.evaluateBoard();
            }
        }

        else
        {
            //Player 2 (Maximising)
            if (player == 2)
            {
                int maxEvaluation = Integer.MIN_VALUE;

                //Loop over all columns.
                for (int i = 0; i < 7; i++)
                {
                    if (game.canInsert(i)) //Handles draw case.
                    {
                        //Insert game piece.
                        game.insert(i, player);

                        maxEvaluation = Math.max(maxEvaluation, minimax(game, depth - 1, 1));

                        //Extract game piece.
                        game.extract(i);
                    }
                }

                return maxEvaluation;
            }

            //Player 1 (Minimising)
            else
            {
                int minEvaluation = Integer.MAX_VALUE;

                for (int i = 0; i < 7; i++)
                {
                    if (game.canInsert(i)) //Handles draw case.
                    {
                        //Insert game piece.
                        game.insert(i, player);

                        minEvaluation = Math.min(minEvaluation, minimax(game, depth - 1, 2));

                        //Extract game piece.
                        game.extract(i);
                    }
                }

                return minEvaluation;
            }
        }
    }

    /** Helper/starter function for minimax algorithm - Alpha beta implementation. */
    public int minimaxStartAlphaBeta(Game game, int depth, int player)
    {
        int bestIndex = 0;
        int bestValue;

        //Player 2 (Maximising)
        if (player == 2)
        {
            bestValue = Integer.MIN_VALUE;

            int index = 3;

            //Loop middle outwards.
            for (int i = 1; i < 8; i++)
            {
                if (game.canInsert(index))
                {
                    //Insert game piece.
                    game.insert(index, player);

                    int tempValue = minimaxAlphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, 1);

                    //Extract game piece.
                    game.extract(index);

                    //Update to the larger value.
                    if (tempValue > bestValue)
                    {
                        bestValue = tempValue;
                        bestIndex = index;
                    }
                }

                if (index > 3)
                {
                    //Loop middle to left.
                    index -= i;
                }

                else
                {
                    //Loop middle to right.
                    index += i;
                }
            }
        }

        //Player 1 (Minimising)
        else
        {
            bestValue = Integer.MAX_VALUE;

            int index = 3;

            //Loop middle outwards.
            for (int i = 1; i < 8; i++)
            {
                if (game.canInsert(index))
                {
                    //Insert game piece.
                    game.insert(index, player);

                    int tempValue = minimaxAlphaBeta(game, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, 2);

                    //Extract game piece.
                    game.extract(index);

                    //Update to the larger value.
                    if (tempValue < bestValue)
                    {
                        bestValue = tempValue;
                        bestIndex = index;
                    }
                }

                if (index > 3)
                {
                    //Loop middle to left.
                    index -= i;
                }

                else
                {
                    //Loop middle to right.
                    index += i;
                }
            }
        }

        return bestIndex;
    }

    /** Recursive minimax algorithm - Alpha beta implementation. */
    private int minimaxAlphaBeta(Game game, int alpha, int beta, int depth, int player)
    {
        //Check for game finished.
        if (depth == 0 || game.isGameEnded())
        {
            if (depth != 0)
            {
                //Return the closest node if a win is found.
                return game.evaluateBoard() * depth;
            }

            else
            {
                return game.evaluateBoard();
            }
        }

        else
        {
            //Player 2 (Maximising)
            if (player == 2)
            {
                int maxEvaluation = Integer.MIN_VALUE;

                //Loop over all columns.
                for (int i = 0; i < 7; i++)
                {
                    if (game.canInsert(i))
                    {
                        //Insert game piece.
                        game.insert(i, player);

                        maxEvaluation = Math.max(maxEvaluation, minimaxAlphaBeta(game, alpha, beta, depth - 1, 1));

                        //Extract game piece.
                        game.extract(i);

                        //Update alpha.
                        alpha = Math.max(alpha, maxEvaluation);

                        if (beta <= alpha)
                        {
                            //Prune branches.
                            break;
                        }
                    }
                }

                return maxEvaluation;
            }

            //Player 1 (Minimising)
            else
            {
                int minEvaluation = Integer.MAX_VALUE;

                //Loop over all columns.
                for (int i = 0; i < 7; i++)
                {
                    if (game.canInsert(i))
                    {
                        //Insert game piece.
                        game.insert(i, player);

                        minEvaluation = Math.min(minEvaluation, minimaxAlphaBeta(game, alpha, beta, depth - 1,2));

                        //Extract game piece.
                        game.extract(i);

                        //Update beta.
                        beta = Math.min(beta, minEvaluation);

                        if (beta <= alpha)
                        {
                            //Prune branches.
                            break;
                        }
                    }
                }

                return minEvaluation;
            }
        }
    }
}