# Connect-4-Minimax-AI

<h2>Overview</h2>
An artificial intelligence to play the board game connect four. 
Developed in Java, the program works by looking ahead at all 
possible future moves and evaluating the game's state.
From here, the best possible move is chosen. It features a pre-computed 
look-up table for fast board evaluations and different player variations including; 
a random insertion AI, a minimax AI, an alpha beta pruning minimax AI and human input. 
A demonstration of a minimax tree is shown below.

<img width="1091" alt="minimax" src="https://user-images.githubusercontent.com/43489707/71558260-2fe92380-2aa5-11ea-8758-45b1302c9ab0.png">

<h2>Board Evaluation</h2>
The evaluation function simply looks all possible ways to make a four in a row and scores the board accordingly. 
Additional points are also awarded for placement in the center column and on specific rows. The specific implementation
of the evaluation function can be seen in TableBuilder.java and can be seen in use in Game.java.

Running time for the algorithm grows exponentially as the depth of the minimax tree is increased. This makes searching 42 moves ahead computationally infeasible. My results when developing the program are shown in the graph below with the implementation of 
basic logic (if statements) for the evaluation function, converting basic logic to a look-up table, and alpha beta pruning.

<img width="1537" alt="Graph" src="https://user-images.githubusercontent.com/43489707/71557885-7be59980-2aa0-11ea-8492-8148550bbd30.PNG">
X = Tree Depth, Y = Milliseconds
