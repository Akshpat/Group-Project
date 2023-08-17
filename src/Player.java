
import java.util.ArrayList;
import java.util.List;

public class Player {
private String name;
private int balance;
private List<Card> hand;


public Player(String name, int initialBalance) {
this.name = name;
this.balance = initialBalance;
this.hand = new ArrayList<>();
}

public String getName() {
return name;
}

public int getBalance() {
return balance;
}

public void adjustBalance(int amount) {
balance += amount;
}

public void addToHand(Card card) {
hand.add(card);
}

public List<Card> getHand() {
return hand;
}

public void clearHand() {
hand.clear();
}

@Override
public String toString() {
return name;
}
}
