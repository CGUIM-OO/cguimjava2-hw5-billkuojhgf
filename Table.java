import java.util.*;

public class Table {
	
	private Deck TableDeck;
	private Player[] player;
	public final int MAXPLAYER = 4;
	private Dealer dealer = new Dealer();
	int[] pos_betArray = new int[MAXPLAYER];
	private ArrayList<Card> PlayerCard = new ArrayList<Card>();
	private ArrayList<Card> DealerCard = new ArrayList<Card>();
	
	
	public Table(int nDeck) {
		TableDeck = new Deck(nDeck);
		player = new Player[MAXPLAYER];
	}

	public void set_player(int pos , Player p){
		player[pos] = p;  
	}
	
	public Player[] get_player(){
		return player;
	}
	
	public void set_dealer(Dealer d){
		dealer = d;
	}
	
	public Card get_face_up_card_of_dealer(){
		return DealerCard.get(1);
	}
	
	private void ask_each_player_about_bets(){
		for(int i = 0 ; i < MAXPLAYER ; i++){
			player[i].sayHello();
			pos_betArray[i] = player[i].makeBet();
		}
	}
	
	private void distribute_cards_to_dealer_and_players(){
		for(int i = 0 ; i < 4 ; i++){
			PlayerCard = new ArrayList<Card>();
			PlayerCard.add(TableDeck.getOneCard(true));
			PlayerCard.add(TableDeck.getOneCard(true));
			player[i].setOneRoundCard(PlayerCard);
		}//for player 1 to player 4
		
		DealerCard.add(TableDeck.getOneCard(false));
		DealerCard.add(TableDeck.getOneCard(true));
		dealer.setOneRoundCard(DealerCard);
		//for dealer card add
		
		System.out.println("Dealer's face up card is ");
		Card get = get_face_up_card_of_dealer();
		get.printCard();
	}
	
	private void ask_each_player_about_hits(){
		for(int i = 0 ; i < MAXPLAYER ; i ++){
			PlayerCard = new ArrayList<Card>();
			PlayerCard = player[i].getOneRoundCard();
			boolean hit = false;
			do {
				hit = player[i].hit_me(this); // this
				int num = 0 ;
				for(Card Num : PlayerCard){
					num += Num.getRank();
				}
				if(num > 21){
					hit = false;
				}
				if (hit) {
					PlayerCard.add(TableDeck.getOneCard(true));
					player[i].setOneRoundCard(PlayerCard);
					System.out.print("Hit! ");
					System.out.println(player[i].getName() + "'s Cards now:");
					for (Card c : PlayerCard) {
						c.printCard();
					}
				}else{
					System.out.println(player[i].getName() + ", Pass hit!");
					System.out.println(player[i].getName() + "'s hit is over");
				}
			} while (hit);
		}//Ask each player if they want cards
	}
	
	private void ask_dealer_about_hits(){
		boolean hit = false;
		do{
			hit = dealer.hit_me(this);
			if(hit){
				DealerCard = new ArrayList<Card>();
				DealerCard = dealer.getOneRoundCard();
				DealerCard.add(TableDeck.getOneCard(true));
				dealer.setOneRoundCard(DealerCard);
			}else{
				System.out.println("Dealer's hit is over!");
			}
		}while(hit);
	}
	
	private void calculate_chips(){
		int DealerNum = 0;
		for(Card Num : DealerCard){
			DealerNum += Num.getRank();
		}
		System.out.print("Dealer's card value is " + DealerNum + " ,Cards : ");
		dealer.printAllCard();
		
		for(int i = 0 ; i < 4 ; i++){
			PlayerCard = new ArrayList<Card>();
			int num = 0;
			
			PlayerCard = player[i].getOneRoundCard();
			System.out.print(player[i].getName() + "'s Cards : ");
			for(Card k : PlayerCard){
				k.printCard();
			}
			for(Card Num : PlayerCard){
				num += Num.getRank();
			}
			System.out.print(player[i].getName() + " card value is " + num);
			if(DealerNum > 21){
				if(num <= 21){
					System.out.print(",Get " + pos_betArray[i] + " Chips , the Chip now is: ");
					player[i].increaseChips(pos_betArray[i]);
					System.out.print(player[i].getCurrentChips());
				}else{
					System.out.print(", chips have no change!, the Chips now is: ");
					System.out.print(player[i].getCurrentChips());
				}
			}else{
				if(DealerNum < num){
					System.out.print(",Get " + pos_betArray[i] + " Chips , the Chip now is: ");
					player[i].increaseChips(pos_betArray[i]);
					System.out.print(player[i].getCurrentChips());
				}else{
					System.out.print(", Loss " + pos_betArray[i] + " Chips, the Chips now is: ");
					player[i].increaseChips(-pos_betArray[i]);
					System.out.print(player[i].getCurrentChips());
				}
			}
			System.out.println();
		}
	}

	public int[] get_palyers_bet() {
		// TODO Auto-generated method stub
		return pos_betArray;
	}
	
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
	
	
	

}