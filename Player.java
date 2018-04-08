/**
 * Class Player - represents a player in a game of cards.
 * @author Adam Yodinsky
 */
public class Player {
	private Deck curr; //current deck of player
	private Deck side; // where the winning goes in a pile
	private String name;
	
	/**
	 * Constructor for a Player in a WarGame of cards
	 * @param name - set name of player
	 */
	public Player(String name) {
		this.name = name;
		curr = new Deck();
		side = new Deck();
	}
	
	/**
	 * @return number of overall cards that the player have
	 */
	public int howManyCards() {
		return curr.getDeck().size() + side.getDeck().size();
	}
	
	/**
	 * remove top card from deck
	 * @return Top card from player's Deck.
	 */
	public Card drawCard() {
		if (curr.isEmpty()) {
			if (!this.refillMixed())
				return null; // return null if player have no cards
		}
		return curr.getDeck().remove(Deck.TOP); // else, remove and return top card
	}
	
	/**
	 * take cards after a win
	 * @param main - deck to take all cards from after a win.
	 */
	public void winCards(Deck main) {
		while(!main.isEmpty())
			this.getSide().getDeck().add(main.getDeck().remove(Deck.TOP));
	}
	
	/**
	 * 
	 * @return true - if Player have no cards in side and current Decks.
	 */
	public boolean outOfCards() {
		if (curr.getDeck().isEmpty() && side.getDeck().isEmpty())
			return true;
		return false;
	}
	
	//refill cards from side deck
	private boolean refill() {
		if (outOfCards()) // check if it is possible to refill
			return false;
		// swap pointers
		Deck temp = curr;
		curr = side;
		side = temp;

		return true;
	}
	
	/**
	 * Refill cards of a Player from side Deck to Current Deck.
	 * @return true if Player is not out of cards.
	 */
	public boolean refillMixed() {
		if (!this.refill())
			return false;
		curr.mix();
		return true;
	}

	/**
	 * @return current Deck of a Player
	 */
	public Deck getCurr() {
		return curr;
	}
	
	/**
	 * @return side Deck of a Player
	 */
	public Deck getSide() {
		return side;
	}
	
	/**
	 * @return string represents the name of a Player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name - the name given for a Player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * return a String representation of a Player. only his name in this case.
	 */
	@Override
	public String toString() {
		return  name;
	}

}
