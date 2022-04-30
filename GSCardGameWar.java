// Gracie Shull
// CS 145 Java 2 Programming
// 4/22/2022
// Lab 4: Card Game of War
// In war, cards are dealt to two people, and then they compare the
// first cards of their deck; If their suit trumps the other, they
// take all the cards. If not, They go to war where they place their
// first two cards and the last one is revealed and the one who
// trumps takes all 6. If another tie, another war. Once someone
// looses all cards, they loose.

// For extra credit, I coded on Intelli-J and also used both stacks
// and queues. Although I could have just used a queue, I wanted
// to practice both! The stack is used for the dealt cards and the
// queue takes care of the cards that have already been used to reuse.
// I also used switched case for attaching strings to ints!

// One error I fixed was the last bit in the battle method
// where the user would go into a war but not have all 3 cards available,
// throwing an exception. I fixed this so the user that doesn't have
// enough automatically looses.

// Another bug is where the suits will be going against each other
// over and over, like aces and deuces, and I can't figure out what's going on here.

import java.util.*;

public class GSCardGameWar {
    public static void main(String[] args) {
        // a new deck of cards is made and then shuffled
        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle();

        // initializes a stack and queue per player
        Stack<Card> playerOne = new Stack<>();
        Stack<Card> playerTwo = new Stack<>();
        Queue<String> playerOneQueue = new PriorityQueue<>();
        Queue<String> playerTwoQueue = new PriorityQueue<>();

        // deals the two players their cards
        for (int i = 1; i <= 52; i = (i + 2)) {
            playerOne.push(myDeckOfCards.dealCard());
            playerTwo.push(myDeckOfCards.dealCard());
        }

        // plays the games while player one's stack still has cards
        // (this is because their cards in their STACKS each
        // dispense cards at the same rate/amount, so it
        // could also be switched for player two's cards)
        while (!playerOne.isEmpty()) {
            stackCards(playerOne, playerTwo, playerOneQueue, playerTwoQueue);
        }

        // Now, the stacks leave the picture and only queues are involved
        // While both queues aren't empty, both players are still alive
        while (!playerOneQueue.isEmpty() && !playerTwoQueue.isEmpty()) {
            queueCards(playerOne, playerTwo, playerOneQueue, playerTwoQueue);
        }

        // if/else loop telling the user who won/ who didn't
        if (playerTwoQueue.isEmpty()) {
            System.out.println("Player one has won this war!");
        } else if (playerOneQueue.isEmpty()) {
            System.out.println("Player two has won this war!");
        } else {
            System.out.println();
        }
    }

    public static void stackCards(Stack<Card> playerOne, Stack<Card> playerTwo,
                                 Queue<String> playerOneQueue, Queue<String> playerTwoQueue) {

        // saving player one's suit for after the suits are compared
        String cardSuitePlayerOne = String.valueOf(playerOne.pop());
        String cardSuite = cardSuitePlayerOne;
        // prints out players suite and converts to int
        int playerOneValue = stringToInt(cardSuite);

        // same process for player two
        String cardSuitePlayerTwo = String.valueOf(playerTwo.pop());
        cardSuite = cardSuitePlayerTwo;
        int playerTwoValue = stringToInt(cardSuite);

        oneCard(playerOneValue, playerTwoValue, playerOne, playerTwo,
                playerOneQueue, playerTwoQueue, cardSuitePlayerOne,
                cardSuitePlayerTwo);
    }

    public static void queueCards(Stack<Card> playerOne, Stack<Card> playerTwo,
                                 Queue<String> playerOneQueue, Queue<String> playerTwoQueue) {

        // same process done in stackCards, but with queues
        String cardSuitePlayerOne = playerOneQueue.remove();
        String cardSuite = cardSuitePlayerOne;
        int playerOneValue = stringToInt(cardSuite);

        String cardSuitePlayerTwo = playerTwoQueue.remove();
        cardSuite = cardSuitePlayerTwo;
        int playerTwoValue = stringToInt(cardSuite);

        oneCard(playerOneValue, playerTwoValue, playerOne, playerTwo,
                playerOneQueue, playerTwoQueue, cardSuitePlayerOne,
                cardSuitePlayerTwo);
    }

    // converts what is a string into an int value to be compared
    public static int stringToInt(String cardSuite) {
        System.out.println(cardSuite);
        String firstTwoLetters = cardSuite.substring(0,2);
        return cardValue(firstTwoLetters);
    }

    public static void oneCard(int playerOneValue, int playerTwoValue,
                               Stack<Card> playerOne, Stack<Card> playerTwo,
                               Queue<String> playerOneQueue, Queue<String> playerTwoQueue,
                               String cardSuitePlayerOne, String cardSuitePlayerTwo) {
        if (playerOneValue < playerTwoValue) {
            System.out.println("Player two beat player one.");
            playerTwoQueue.add(cardSuitePlayerOne);
            playerTwoQueue.add(cardSuitePlayerTwo);
        } else if (playerOneValue > playerTwoValue) {
            System.out.println("Player one beat player two.");
            playerOneQueue.add(cardSuitePlayerOne);
            playerOneQueue.add(cardSuitePlayerTwo);
        } else { // playerOneValue = playerTwoValue
            System.out.println("Oh no, it looks like there is a war.");
            battle(playerOne, playerTwo, playerOneQueue, playerTwoQueue);
        }
    }

    public static void battle(Stack<Card> playerOne, Stack<Card> playerTwo,
                             Queue<String> playerOneQueue, Queue<String> playerTwoQueue) {
        String playerOne1;
        String playerOne2;
        String cardSuitePlayerOne;
        String playerTwo1;
        String playerTwo2;
        String cardSuitePlayerTwo;
        String cardSuite;

        // this means there are 3 or more cards in stack, enough for war
        if (playerOne.size() >= 3) {
            // grabs first 3 cards of player 1, revealing the first
            playerOne1 = String.valueOf(playerOne.pop());
            playerOne2 = String.valueOf(playerOne.pop());
            cardSuitePlayerOne = String.valueOf(playerOne.pop());
            // converts string to int
            cardSuite = cardSuitePlayerOne;
            int playerOneValue = stringToInt(cardSuite);

            // same process for player 2
            playerTwo1 = String.valueOf(playerTwo.pop());
            playerTwo2 = String.valueOf(playerTwo.pop());
            cardSuitePlayerTwo = String.valueOf(playerTwo.pop());
            cardSuite = cardSuitePlayerTwo;
            int playerTwoValue = stringToInt(cardSuite);

            battleOutcome(playerOneValue, playerTwoValue, playerOneQueue, playerTwoQueue,
                    playerOne1, playerOne2, playerTwo1, playerTwo2,
                    cardSuitePlayerOne, cardSuitePlayerTwo, playerOne, playerTwo);
        } else if (playerOne.size() == 2) {
            // same process as before, but replaces stack with queue
            playerOne1 = String.valueOf(playerOne.pop());
            playerOne2 = String.valueOf(playerOne.pop());
            cardSuitePlayerOne = playerOneQueue.remove();
            cardSuite = cardSuitePlayerOne;
            int playerOneValue = stringToInt(cardSuite);

            playerTwo1 = String.valueOf(playerTwo.pop());
            playerTwo2 = String.valueOf(playerTwo.pop());
            cardSuitePlayerTwo = playerTwoQueue.remove();
            cardSuite = cardSuitePlayerTwo;
            int playerTwoValue = stringToInt(cardSuite);

            battleOutcome(playerOneValue, playerTwoValue, playerOneQueue, playerTwoQueue,
                    playerOne1, playerOne2, playerTwo1, playerTwo2,
                    cardSuitePlayerOne, cardSuitePlayerTwo, playerOne, playerTwo);
        } else if (playerOne.size() == 1) {
            playerOne1 = String.valueOf(playerOne.pop());
            playerOne2 = playerOneQueue.remove();
            cardSuitePlayerOne = playerOneQueue.remove();
            cardSuite = cardSuitePlayerOne;
            int playerOneValue = stringToInt(cardSuite);

            playerTwo1 = String.valueOf(playerTwo.pop());
            playerTwo2 = playerTwoQueue.remove();
            cardSuitePlayerTwo = playerTwoQueue.remove();
            cardSuite = cardSuitePlayerTwo;
            int playerTwoValue = stringToInt(cardSuite);

            battleOutcome(playerOneValue, playerTwoValue, playerOneQueue, playerTwoQueue,
                    playerOne1, playerOne2, playerTwo1, playerTwo2,
                    cardSuitePlayerOne, cardSuitePlayerTwo, playerOne, playerTwo);
        } else { // for just the queues
            if (playerOneQueue.size() >= 3 && playerTwoQueue.size() >= 3) {
                playerOne1 = playerOneQueue.remove();
                playerOne2 = playerOneQueue.remove();
                cardSuitePlayerOne = playerOneQueue.remove();
                cardSuite = cardSuitePlayerOne;
                int playerOneValue = stringToInt(cardSuite);

                playerTwo1 = playerTwoQueue.remove();
                playerTwo2 = playerTwoQueue.remove();
                cardSuitePlayerTwo = playerTwoQueue.remove();
                cardSuite = cardSuitePlayerTwo;
                int playerTwoValue = stringToInt(cardSuite);

                battleOutcome(playerOneValue, playerTwoValue, playerOneQueue, playerTwoQueue,
                        playerOne1, playerOne2, playerTwo1, playerTwo2,
                        cardSuitePlayerOne, cardSuitePlayerTwo, playerOne, playerTwo);
            } else if (playerOneQueue.size() < 3) {
                playerOneQueue.clear();
            } else if (playerTwoQueue.size() < 3) {
                playerTwoQueue.clear();
            } else {
                System.out.println("Error.");
            }
        }
    }

    public static void battleOutcome(int playerOneValue, int playerTwoValue,
                                     Queue<String> playerOneQueue, Queue<String> playerTwoQueue,
                                     String playerOne1, String playerOne2,
                                     String playerTwo1, String playerTwo2,
                                     String cardSuitePlayerOne, String cardSuitePlayerTwo,
                                     Stack<Card> playerOne, Stack<Card> playerTwo) {

        if (playerOneValue < playerTwoValue) {
            System.out.println("Player two beat player one in this war.");
            System.out.println("All cards goes to player two.");

            playerTwoQueue.add(playerOne1);
            playerTwoQueue.add(playerOne2);
            playerTwoQueue.add(playerTwo1);
            playerTwoQueue.add(playerTwo2);
            playerTwoQueue.add(cardSuitePlayerOne);
            playerTwoQueue.add(cardSuitePlayerTwo);
        } else if (playerOneValue > playerTwoValue) {
            System.out.println("Player one beat player two in this war.");
            System.out.println("All cards goes to player one.");

            playerOneQueue.add(playerOne1);
            playerOneQueue.add(playerOne2);
            playerOneQueue.add(playerTwo1);
            playerOneQueue.add(playerTwo2);
            playerOneQueue.add(cardSuitePlayerOne);
            playerOneQueue.add(cardSuitePlayerTwo);
        } else { // playerOneValue == playerTwoValue
            // here is where the war is started
            System.out.println("Oh no, it looks like there is ANOTHER war!");
            battle(playerOne, playerTwo, playerOneQueue, playerTwoQueue);
        }
    }

    // Assigns the suits to a number
    public static int cardValue(String firstTwoLetters) {
        switch (firstTwoLetters) {
            case "Ac":
                return 1;
            case "De":
                return 2;
            case "Th":
                return 3;
            case "Fo":
                return 4;
            case "Fi":
                return 5;
            case "Si":
                return 6;
            case "Se":
                return 7;
            case "Ei":
                return 8;
            case "Ni":
                return 9;
            case "Te":
                return 10;
            case "Ja":
                return 11;
            case "Qu":
                return 12;
            case "Ki":
                return 13;
            default:
                return 0;
        }
    }
}