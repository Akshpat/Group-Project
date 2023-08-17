import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
private List<Card> cards;

public Deck() {
initializeDeck();
}

private void initializeDeck() {
cards = new ArrayList<>();
String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

for (String suit : suits) {
for (String rank : ranks) {
cards.add(new Card(rank, suit));
}
}
shuffle();
}

public void shuffle() {
Collections.shuffle(cards);
}

public Card drawCard() {
if (cards.isEmpty()) {
initializeDeck(); // If the deck is empty, reinitialize and shuffle.
}
return cards.remove(0);
}
}
