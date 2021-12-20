package eunchan;

import java.util.ArrayList;

class Player {
	ArrayList<Card> list = new ArrayList<Card>();
	
	public void getCard(Card card) {
		list.add(card);
	}
	
	public void showCards() {
		System.out.println(list);
	}
}