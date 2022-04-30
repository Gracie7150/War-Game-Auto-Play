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

import java.security.SecureRandom;

public class GSDeckOfCards {
    // random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;

    private Card[] deck = new Card[NUMBER_OF_CARDS];
    private int currentCard = 0;

    // constructor fills deck of cards
    public DeckOfCards() {
        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public void shuffle() {
        currentCard = 0;

        // for each Card, pick another random card (0-51) and swap them
        for (int first = 0; first < deck.length; first++) {
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    public Card dealCard() {
        // determine cards remain to be dealt
        if (currentCard < deck.length) {
            return deck[currentCard++]; // return current card in array
        } else {
            return null; // return null to indicate all cards are dealt
        }
    }
}