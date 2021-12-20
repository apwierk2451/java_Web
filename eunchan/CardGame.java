package eunchan;

public class CardGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Deck deck = new Deck();
		deck.shuffle();
		Player p1 = new Player();
		Player p2 = new Player();
		p1.getCard(deck.deal());
		p2.getCard(deck.deal());
		p1.showCards();
		p2.showCards();
	}

}
