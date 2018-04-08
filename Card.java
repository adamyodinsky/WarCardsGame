
/**
 * class Card - represents a Card from a Deck.
 * @author Adam Yodinsky
 */
public class Card {
	
	
	
	public static enum Shape {heart, diamond, club, spade};
	
	public static enum Suit {
		Deuce(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8),
		Nine(9), Ten(10), Jack(11), Queen(12), King(13), Ace(14);
		int value;
		
		private Suit(int val) {
			value = val;
		}
		
		private int getValue(){
			return value;
		}
	}
	
	// number of suites and shapes
	public static final int SHAPES = 4;
	public static final int SUITES = 13;
	
	//fields
	private Shape shape;
	private Suit suit;
	
	/**
	 * Constructor of Card
	 * @param shape - shape of card from Card.Shape
	 * @param suit - suit of card from Card.Suit
	 */
	public Card(Shape shape, Suit suit) {
		this.shape = shape;
		this.suit = suit;
	}

	/**
	 * 
	 * @return Shape of card
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * 
	 * @param Shape of card
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	/**
	 * 
	 * @return Suit of card
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * 
	 * @param Suit of card
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	/**
	 * 
	 * @return value of card
	 */
	public int getVal(){
		return this.getSuit().getValue();
	}
	
	/**
	 *  return a string representation of a Card
	 */
	@Override
	public String toString() {
		return  suit +" "+ shape;
	}
}
