This is a java code that I have written as an amelioration of the file "Protect The Wall"

This code display two (2) rectangles (players 1 on the left and player 2 on the right) and a ball and keeps count of the scores
	
	**Score**
	Every time the ball hit the wall(Edge) behind a paddle, the score increments by one (+1)
		if the ball hit the left wall, player2 get one (1) point
		if the ball hit the wall on the right, player1 gets one (1) point
	**Paddle**
	The player1 uses "w" to move the paddle up and "s" to move down
	The player2 uses "o" to move the paddle up and "l" to move down

	**Ball**
	Every time the ball hits any wall or the paddle, it bounces back

	**End**
	The game concludes under two circumstances: either Player 1 achieves a score of 50 points and is declared the winner, 
	Or Player 2 reaches 50 points and wins the game.  
	Upon a player's victory, the following message will be displayed on the screen: (PlayerX Wins).