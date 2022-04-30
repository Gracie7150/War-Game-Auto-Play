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

public class GSCard {
    private final String face; // face of card
    private final String suit; // face of suit

    // two-argument constructor initializes card's face and suit
    public Card(String cardFace, String cardSuit) {
        this.face = cardFace; // initializes face of card
        this.suit = cardSuit; // initializes suit of card
    }

    // return String representation of card
    public String toString() {
        return face + " of " + suit;
    }
}