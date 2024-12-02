# Texas Hold'em Game Win Rate Simulator
A Java-based poker game simulation with ranking logic and testability.

Rules of the game will be based on explanation on Wikipedia.

https://en.wikipedia.org/wiki/Texas_hold_%27em

This project is a Texas Hold'em poker game that evaluates hand rankings and calculates win probabilities.
It automates the card dealing, community card revelation (flop, turn, river), and final showdown to determine the winner.

The goal is to provide players with a real-time evaluation of their hand strength and potential win rates at each game stage.


## Usage
### Games Setup:
It initializes a array list of Rank objects (RoyalFlush, StraightFlush, etc.) ordered from highest to lowest rank. 
This list is used to determine hand rankings during the game. 
User also need to enter the number of the players.

### Hand Card Setup and Deck Initialization:
User will asked to enter their hand cards and the community cards in the format of suit + rank,

e.g. sT -> ♠ Spade 10; cA -> ♣ Club Ace.

once user update these cards, a Deck object will be created, representing the well shuffled deck without the cards we entered. 
This feature helps simulate each stage in a real game. 
(pre-flop, flop, turn, river)



### Simulation of games:
The winRate() function takes the current community cards and hand cards,
and estimates the player's chance of winning by simulating 50000 games. 
It also provides winning counts of each rank.

This gives players an idea of their odds as the game progresses.




## Show Case

    Enter how many players tables:
            7
    Noted : enter card with suit + rank.
    e.g. cA c2 c3
    e.g. sT sJ sK

    >> New Game <<

    Enter your hands: sT s6
    You have :
            ♠ 10 ♠ 6

    Out of 50000 games :
    win by Royal Flush	0 times.
    win by Straight Flush	18 times.
    win by Four of a Kind	57 times.
    win by Full House	928 times.
    win by Flush	1371 times.
    win by Straight	1430 times.
    win by Three of a Kind	1007 times.
    win by Two Pair	2011 times.
    win by One Pair	583 times.
    win by High Card	0 times.

    You have 0.1481 chance to win

    Press enter to continue, Press r to reset the game .....
    Enter your Community cards: c6 d9 dT
    You have :
            ♠ 10 ♠ 6
    community cards :
            ♣ 6 ♦ 9 ♦ 10

    Out of 50000 games :
    win by Royal Flush	0 times.
    win by Straight Flush	0 times.
    win by Four of a Kind	82 times.
    win by Full House	7424 times.
    win by Flush	0 times.
    win by Straight	168 times.
    win by Three of a Kind	0 times.
    win by Two Pair	16210 times.
    win by One Pair	0 times.
    win by High Card	0 times.

    You have 0.47768 chance to win

    Press enter to continue, Press r to reset the game .....
    Enter your Community cards: d6
    You have :
            ♠ 10 ♠ 6
    community cards :
            ♣ 6 ♦ 9 ♦ 10 ♦ 6

    Out of 50000 games :
    win by Royal Flush	0 times.
    win by Straight Flush	0 times.
    win by Four of a Kind	894 times.
    win by Full House	45667 times.
    win by Flush	0 times.
    win by Straight	0 times.
    win by Three of a Kind	0 times.
    win by Two Pair	0 times.
    win by One Pair	0 times.
    win by High Card	0 times.

    You have 0.93122 chance to win

    Press enter to continue, Press r to reset the game .....
    Enter your Community cards: cT
    You have :
            ♠ 10 ♠ 6
    community cards :
            ♣ 6 ♦ 9 ♦ 10 ♦ 6 ♣ 10

    Out of 50000 games :
    win by Royal Flush	0 times.
    win by Straight Flush	0 times.
    win by Four of a Kind	0 times.
    win by Full House	49110 times.
    win by Flush	0 times.
    win by Straight	0 times.
    win by Three of a Kind	0 times.
    win by Two Pair	0 times.
    win by One Pair	0 times.
    win by High Card	0 times.

    You have 0.9822 chance to win

