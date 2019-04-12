package ood;

import java.util.List;

public class DeckOfCards {

}

enum CardType {
	SPADE, DIAMOND, HEART, CLUBS,
}

class Card {
	CardType type;
	int number;

	int getValue() {
		return number;
	}
}

class Deck {
	List<Card> cards;

	void shuffle() {

	}

	int getRemainingCardCount() {
		return cards.size();
	}

	Card dealCard() {
		if (!cards.isEmpty()) {
			return cards.remove(0);
		}
		return null;
	}
}

class BlackJackCard extends Card {
	@Override
	int getValue() {
		if (number > 10) {
			return 10;
		} else {
			return number;
		}
	}
}