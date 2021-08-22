# Blackjack 
This blackjack game simulates two players - Sam and the dealer playing the game. The following rules are implemente as per the spec provided.

* If either player has Blackjack in their initial hand Sam wins 
* The dealer wins when both players start with 22
* Sam will stop drawing cards once their total reaches 17 or higher.
* Sam will lose the game if their total is higher than 21.
* The dealer will start drawing when Sam has finished.
* The dealer will stop drawing once their total is higher than Sam's.
* The dealer loses if their card total is higher than 21.
* The player with the highest score wins the game.

Other assumptions made were:

* Ties are always in favour of the dealer.
* The file provided must contain 52 unique cards.

## Implementation Details
The implementation was programmed in Java 8 (1.8) and tested with JUnit 5.4.2

## Usage
Java 8 is required to run the implementation. The program can be provided with an input txt file with the following format:
```shell
D2,D4,H7,SJ,..,S9,CA,CK
```
Where the input contains 52 unique cards.

The implementation can be found on the [releases page](https://github.com/mcallistertyler/blackjack-java/releases) or under the `out/artifacts/Blackjack_jar`. It can also be run in an IDE using the `Blackjack.java` class.
Via the command line using the jar the following command will run the program:

```shell
java -jar Blackjack.jar ./path_to_file
```

If run without any input the program will generate a shuffled deck of 52 cards.

By default the input file will not be shuffled. Shuffling the deck from file input can be done via
```shell
java -jar Blackjack.jar ./path_to_file true
```

Some example decks can be found under the `example-decks` directory while incorrectly formatted examples can be found under the `invalid-decks` directory.

## Output
The program will simulate a Blackjack game and output the winner at the top of the output, along with the final hands for both players. For example, in the below example the dealer has won because they drew 21 in their initial hand.

```shell
dealer
sam: CJ, S9
dealer: D10, SA
```
