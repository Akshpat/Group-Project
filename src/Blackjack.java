import java.util.Scanner;

public class Blackjack {
private Deck deck;
private Player player;
private Player dealer;

public Blackjack() {
deck = new Deck();
}

public void startGame() {
Scanner scanner = new Scanner(System.in);
System.out.println("Welcome to Blackjack!");
System.out.print("Enter your name: ");
String playerName = scanner.nextLine();
System.out.print("Enter your initial balance: ");
int initialBalance = scanner.nextInt();

player = new Player(playerName, initialBalance);
dealer = new Player("Dealer", 0); // Dealer doesn't need a balance.

playRound();

scanner.close();
}

private void playRound() {
Scanner scanner = new Scanner(System.in);

// Deal initial cards to player and dealer
player.clearHand();
dealer.clearHand();
player.addToHand(deck.drawCard());
player.addToHand(deck.drawCard());
dealer.addToHand(deck.drawCard());
dealer.addToHand(deck.drawCard());

// Display the initial hands
System.out.println("Your hand: " + player.getHand());
System.out.println("Dealer's hand: " + dealer.getHand().get(0));

// Player's turn
while (true) {
System.out.print("Do you want to hit or stand? (h/s): ");
String choice = scanner.next().toLowerCase();
if (choice.equals("h")) {
player.addToHand(deck.drawCard());
System.out.println("Your hand: " + player.getHand());
if (getHandValue(player) > 21) {
System.out.println("Bust! You lose.");
player.adjustBalance(-10); // Adjust balance after losing the round.
break;
}
} else if (choice.equals("s")) {
break;
} else {
System.out.println("Invalid input. Please enter 'h' or 's'.");
}
}

// Dealer's turn
while (getHandValue(dealer) < 17) {
dealer.addToHand(deck.drawCard());
}

// Determine the winner
int playerValue = getHandValue(player);
int dealerValue = getHandValue(dealer);

System.out.println("Your hand: " + player.getHand() + " (Value: " + playerValue + ")");
System.out.println("Dealer's hand: " + dealer.getHand() + " (Value: " + dealerValue + ")");

if (playerValue <= 21 && (dealerValue > 21 || playerValue > dealerValue)) {
System.out.println("Congratulations! You win.");
player.adjustBalance(10); // Adjust balance after winning the round.
} else if (dealerValue <= 21 && (playerValue > 21 || playerValue < dealerValue)) {
System.out.println("You lose. Better luck next time.");
player.adjustBalance(-10); // Adjust balance after losing the round.
} else {
System.out.println("It's a tie.");
}

// Check if the player has enough balance to play another round
if (player.getBalance() >= 10) {
System.out.println("Your current balance: " + player.getBalance());
System.out.print("Do you want to play another round? (y/n): ");
String playAgain = scanner.next().toLowerCase();
if (playAgain.equals("y")) {
playRound();
} else {
System.out.println("Thank you for playing Blackjack!");
}
} else {
System.out.println("Insufficient balance. Game over.");
}

scanner.close();
}

private int getHandValue(Player player) {
int value = 0;
int aces = 0;

for (Card card : player.getHand()) {
String rank = card.getRank();
if (rank.equals("Ace")) {
aces++;
value += 11;
} else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
value += 10;
} else {
value += Integer.parseInt(rank);
}
}

while (aces > 0 && value > 21) {
value -= 10;
aces--;
}

return value;
}

public static void main(String[] args) {
Blackjack blackjack = new Blackjack();
blackjack.startGame();
}
}

