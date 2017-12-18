import java.util.ArrayList;

public class Player extends Person{

	private String name;// input the player's name
	private int chips;// input the chips(籌碼)that the player have
	private int bet;// input the bet that the player add for this game
	private ArrayList<Card> oneRoundCard;

	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}

	public String getName() {
		return name;
	}

	public int makeBet() {
		bet = 1;
		if(getCurrentChips() == 0){
			bet = 0;
		}
		return bet;
	}


	public int getCurrentChips() {
		return chips;
	}

	public void increaseChips(int diff) {
		chips = chips + diff;
	}

	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

	@Override
	public boolean hit_me(Table tbl) {
		int num = 0;
		oneRoundCard = this.getOneRoundCard();
		for (Card k : oneRoundCard) {
			num = num + k.getRank();
		}
		if (num <= 16) {
			return true;
		} else {
			return false;
		}
	}

}
