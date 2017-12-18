
public class Card {
	private Suit suit;
	private int rank;

	public enum Suit {
		Club, Diamond, Heart, Spade
	}

	public Card(Suit s, int r) {
		suit = s;
		rank = r;
	}

	public void printCard() {
		if (rank == 1) {
			System.out.println(suit + " , " + "Ace");
		} else if (rank == 11) {
			System.out.println(suit + " , " + "J");
		} else if (rank == 12) {
			System.out.println(suit + " , " + "Q");
		} else if (rank == 13) {
			System.out.println(suit + " , " + "K");
		} else {
			System.out.println(suit + " , " + rank);
		}

	}

	public Suit getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}

}
