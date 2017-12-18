
import java.util.*;

public class Deck {

	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard;
	public int nUsed = -1;

	public Deck(int nDeck) {
		
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		
		for (int i = 1; i <= nDeck; i++) {
			for (Card.Suit j : Card.Suit.values()) {
				for (int k = 1; k <= 13; k++) {
					Card card = new Card(j, k);
					cards.add(card);
				}
			}
		}
		
		shuffle();
	}

	public void printDeck() {
		
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).printCard();
		}
	}

	public Card getOneCard(boolean isOpened) {

		if (isOpened) {
			openCard.add(cards.get(nUsed));
		}

		nUsed += 1;
		
		if (nUsed == cards.size()) {
			usedCard.clear();
			shuffle();
		}

		usedCard.add(cards.get(nUsed));
		return cards.get(nUsed);

	}

	public void shuffle() {
		
		ArrayList<Card> NewCard = new ArrayList<Card>();
		Random rnd = new Random();
		Card c;
		
		for (int i = 1; i <= 52; i++) {
			do {
				c = cards.get(rnd.nextInt(52));
			} while (NewCard.contains(c));
			NewCard.add(c);
		}
		
		cards.clear();
		cards = NewCard;
		openCard.clear();

		nUsed = 0;

	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}

	public ArrayList<Card> getOpenCard() {
		return openCard;
	}

}
