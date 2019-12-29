/*
 *  Brandon Hopkins - C3290146
 *  Software Engineering - COMP2230
 *  Assignment 1
 */

public class Game
{
    //Board state.
    private int[] board;
    private int[] level;
    private int inserts;

    //Board representation.
    // 5 [35][36][37][38][39][40][41]
    // 4 [28][29][30][31][32][33][34]
    // 3 [21][22][23][24][25][26][27]
    // 2 [14][15][16][17][18][19][20]
    // 1 [ 7][ 8][ 9][10][11][12][13]
    // 0 [ 0][ 1][ 2][ 3][ 4][ 5][ 6]
    //     0   1   2   3   4   5   6

    //Board size.
    private final int HEIGHT = 6;
    private final int WIDTH = 7;

    //Game over flag.
    private boolean gameEnded;

    //Lookup tables.
    private final int[] hash_4 = TableBuilder.buildTable(4);
    private final int[] hash_5 = TableBuilder.buildTable(5);
    private final int[] hash_6 = TableBuilder.buildTable(6);
    private final int[] hash_7 = TableBuilder.buildTable(7);

    /** Class Constructor. */
    Game()
    {
        //Initialise game board.
        initialise();
    }

    /** Initialises the board to default values. */
    public void initialise()
    {
        //Initialise variables.
        this.board = new int[HEIGHT * WIDTH];
        this.level = new int[WIDTH];
        this.inserts = 0;
        this.gameEnded = false;
    }

    /** Returns whether the column can be played in. */
    public boolean canInsert(int column)
    {
        return level[column] < HEIGHT;
    }

    /** Must be called directly after canInsert. */
    public void insert(int column, int player)
    {
        //Insert piece.
        board[WIDTH * level[column] + column] = player;

        //Increment column level.
        level[column]++;

        //Increment total inserts.
        inserts++;

        if (isWinningInsert(column) || inserts == 42)
        {
            //Set game to ended.
            gameEnded = true;
        }
    }

    /** Must be called directly after insert. */
    public void extract(int column)
    {
        //Decrement total inserts.
        inserts--;

        //Increment column level.
        level[column]--;

        //Insert piece.
        board[WIDTH * level[column] + column] = 0;

        //Set game to not ended.
        gameEnded = false;
    }


    /** Must be called directly before insert. */
    public boolean isWinningInsert(int column)
    {
        //Evaluation score.
        int score = 0;

        //Row inserted on.
        int row = WIDTH * (level[column] - 1);

        // Vertical (|)
        score += hash_6[243 * (board[column]) + 81 * (board[column + 7]) + 27 * (board[column + 14]) + 9 * (board[column + 21]) + 3 * (board[column + 28]) + (board[column + 35])];

        // Horizontal (-)
        score += hash_7[729 * (board[row]) + 243 * (board[row + 1]) + 81 * (board[row + 2]) + 27 * (board[row + 3]) + 9 * (board[row + 4]) + 3 * (board[row + 5]) + (board[row + 6])];

        // Diagonal (\)
        score += hash_4[                                       27 * (board[ 3]) + 9 * (board[ 9]) + 3 * (board[15]) + (board[21])];
        score += hash_5[                    81 * (board[ 4]) + 27 * (board[10]) + 9 * (board[16]) + 3 * (board[22]) + (board[28])];
        score += hash_6[243 * (board[ 5]) + 81 * (board[11]) + 27 * (board[17]) + 9 * (board[23]) + 3 * (board[29]) + (board[35])];
        score += hash_6[243 * (board[ 6]) + 81 * (board[12]) + 27 * (board[18]) + 9 * (board[24]) + 3 * (board[30]) + (board[36])];
        score += hash_5[                    81 * (board[13]) + 27 * (board[19]) + 9 * (board[25]) + 3 * (board[31]) + (board[37])];
        score += hash_4[                                       27 * (board[20]) + 9 * (board[26]) + 3 * (board[32]) + (board[38])];

        // Diagonal (/)
        score += hash_4[                                       27 * (board[ 3]) + 9 * (board[11]) + 3 * (board[19]) + (board[27])];
        score += hash_5[                    81 * (board[ 2]) + 27 * (board[10]) + 9 * (board[18]) + 3 * (board[26]) + (board[34])];
        score += hash_6[243 * (board[ 1]) + 81 * (board[ 9]) + 27 * (board[17]) + 9 * (board[25]) + 3 * (board[33]) + (board[41])];
        score += hash_6[243 * (board[ 0]) + 81 * (board[ 8]) + 27 * (board[16]) + 9 * (board[24]) + 3 * (board[32]) + (board[40])];
        score += hash_5[                    81 * (board[ 7]) + 27 * (board[15]) + 9 * (board[23]) + 3 * (board[31]) + (board[39])];
        score += hash_4[                                       27 * (board[14]) + 9 * (board[22]) + 3 * (board[30]) + (board[38])];

        //Return if a win has been found.
        return  score < -90000 || 90000 < score;
    }

    /** Evaluates the current board state and returns a score. */
    public int evaluateBoard()
    {
        //Evaluation score.
        int score = 0;



        // Horizontal Score (-)
        // [ ][ ][ ][ ][ ][ ][ ] 5
        // [ ][ ][ ][ ][ ][ ][ ] 4
        // [ ][ ][ ][X][X][X][X] 3
        // [ ][ ][X][X][X][X][ ] 2
        // [ ][X][X][X][X][ ][ ] 1
        // [X][X][X][X][ ][ ][ ] 0
        //  0  1  2  3  4  5  6

        score += hash_7[729 * (board[ 0]) + 243 * (board[ 1]) + 81 * (board[ 2]) + 27 * (board[ 3]) + 9 * (board[ 4]) + 3 * (board[ 5]) + (board[ 6])];
        score += hash_7[729 * (board[ 7]) + 243 * (board[ 8]) + 81 * (board[ 9]) + 27 * (board[10]) + 9 * (board[11]) + 3 * (board[12]) + (board[13])] * 3;
        score += hash_7[729 * (board[14]) + 243 * (board[15]) + 81 * (board[16]) + 27 * (board[17]) + 9 * (board[18]) + 3 * (board[19]) + (board[20])] * 3;
        score += hash_7[729 * (board[21]) + 243 * (board[22]) + 81 * (board[23]) + 27 * (board[24]) + 9 * (board[25]) + 3 * (board[26]) + (board[27])];
        score += hash_7[729 * (board[28]) + 243 * (board[29]) + 81 * (board[30]) + 27 * (board[31]) + 9 * (board[32]) + 3 * (board[33]) + (board[34])];
        score += hash_7[729 * (board[35]) + 243 * (board[36]) + 81 * (board[37]) + 27 * (board[38]) + 9 * (board[39]) + 3 * (board[40]) + (board[41])];



        // Vertical Score (|)
        // [ ][ ][ ][ ][X][ ][ ] 5
        // [ ][ ][X][ ][X][ ][ ] 4
        // [X][ ][X][ ][X][ ][ ] 3
        // [X][ ][X][ ][X][ ][ ] 2
        // [X][ ][X][ ][ ][ ][ ] 1
        // [X][ ][ ][ ][ ][ ][ ] 0
        //  0  1  2  3  4  5  6

        score += hash_6[243 * (board[ 0]) + 81 * (board[ 7]) + 27 * (board[14]) + 9 * (board[21]) + 3 * (board[28]) + (board[35])];
        score += hash_6[243 * (board[ 1]) + 81 * (board[ 8]) + 27 * (board[15]) + 9 * (board[22]) + 3 * (board[29]) + (board[36])];
        score += hash_6[243 * (board[ 2]) + 81 * (board[ 9]) + 27 * (board[16]) + 9 * (board[23]) + 3 * (board[30]) + (board[37])];
        score += hash_6[243 * (board[ 3]) + 81 * (board[10]) + 27 * (board[17]) + 9 * (board[24]) + 3 * (board[31]) + (board[38])];
        score += hash_6[243 * (board[ 4]) + 81 * (board[11]) + 27 * (board[18]) + 9 * (board[25]) + 3 * (board[32]) + (board[39])];
        score += hash_6[243 * (board[ 5]) + 81 * (board[12]) + 27 * (board[19]) + 9 * (board[26]) + 3 * (board[33]) + (board[40])];
        score += hash_6[243 * (board[ 6]) + 81 * (board[13]) + 27 * (board[20]) + 9 * (board[27]) + 3 * (board[34]) + (board[41])];



        // Diagonal Score (\)
        // [ ][ ][ ][X][ ][ ][ ] 5
        // [ ][ ][ ][ ][X][ ][ ] 4
        // [X][ ][ ][ ][ ][X][ ] 3
        // [ ][X][ ][ ][ ][ ][X] 2
        // [ ][ ][X][ ][ ][ ][ ] 1
        // [ ][ ][ ][X][ ][ ][ ] 0
        //  0  1  2  3  4  5  6

        score += hash_4[                                       27 * (board[ 3]) + 9 * (board[ 9]) + 3 * (board[15]) + (board[21])] * 2;
        score += hash_5[                    81 * (board[ 4]) + 27 * (board[10]) + 9 * (board[16]) + 3 * (board[22]) + (board[28])] * 2;
        score += hash_6[243 * (board[ 5]) + 81 * (board[11]) + 27 * (board[17]) + 9 * (board[23]) + 3 * (board[29]) + (board[35])] * 2;
        score += hash_6[243 * (board[ 6]) + 81 * (board[12]) + 27 * (board[18]) + 9 * (board[24]) + 3 * (board[30]) + (board[36])] * 2;
        score += hash_5[                    81 * (board[13]) + 27 * (board[19]) + 9 * (board[25]) + 3 * (board[31]) + (board[37])];
        score += hash_4[                                       27 * (board[20]) + 9 * (board[26]) + 3 * (board[32]) + (board[38])];



        // Diagonal Points (/)
        // [ ][ ][ ][X][ ][ ][ ] 5
        // [ ][ ][X][ ][ ][ ][ ] 4
        // [ ][X][ ][ ][ ][ ][X] 3
        // [X][ ][ ][ ][ ][X][ ] 2
        // [ ][ ][ ][ ][X][ ][ ] 1
        // [ ][ ][ ][X][ ][ ][ ] 0
        //  0  1  2  3  4  5  6

        score += hash_4[                                       27 * (board[ 3]) + 9 * (board[11]) + 3 * (board[19]) + (board[27])] * 2;
        score += hash_5[                    81 * (board[ 2]) + 27 * (board[10]) + 9 * (board[18]) + 3 * (board[26]) + (board[34])] * 2;
        score += hash_6[243 * (board[ 1]) + 81 * (board[ 9]) + 27 * (board[17]) + 9 * (board[25]) + 3 * (board[33]) + (board[41])] * 2;
        score += hash_6[243 * (board[ 0]) + 81 * (board[ 8]) + 27 * (board[16]) + 9 * (board[24]) + 3 * (board[32]) + (board[40])] * 2;
        score += hash_5[                    81 * (board[ 7]) + 27 * (board[15]) + 9 * (board[23]) + 3 * (board[31]) + (board[39])];
        score += hash_4[                                       27 * (board[14]) + 9 * (board[22]) + 3 * (board[30]) + (board[38])];

        return score;
    }

    /** Returns if the game has ended. */
    public boolean isGameEnded()
    {
        return gameEnded;
    }

    /** Returns total game inserts. */
    public int totalInserts()
    {
        return inserts;
    }

    /** Prints the board to console. */
    public void printBoard()
    {
        System.out.println(this.toString());
    }

    /** Returns the board as a string. */
    public String toString()
    {
        StringBuilder boardState = new StringBuilder();

        for (int y = HEIGHT - 1; y >= 0; y--)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                switch (board[y * WIDTH + x])
                {
                    case 1:
                        boardState.append("|X");
                        break;
                    case 0:
                        boardState.append("| ");
                        break;
                    case 2:
                        boardState.append("|O");
                        break;
                }
            }

            boardState.append("|\n");
        }

        boardState.append(" 0 1 2 3 4 5 6\n");
        boardState.append("_______________\n");

        return boardState.toString();
    }
}