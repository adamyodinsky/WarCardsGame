
import java.util.ArrayList;
import java.util.Random;

/**
 * Class Deck - represents a Deck of Cards
 * @author Adam Yodinsky
 */
public class Deck {
	
	public static final int TOP = 0;
	private ArrayList<Card> deck;

	/**
	 * Constructor of Deck.
	 * initialize an empty Deck with a dynamic size
	 */
	public Deck() {
		deck = new ArrayList<Card>();
	}
	
	
	/**
	 * initialize and fill deck with a full range of all 52 cards.
	 */
	public void initializeDeck() {
		Card temp;
		// fill Deck with cards of all suits and shapes
		for (int i = 0; i < Card.SHAPES; i++) {
			for (int j = 0; j < Card.SUITES; j++) {
				temp = new Card(Card.Shape.values()[i], Card.Suit.values()[j]);
				deck.add(temp);
			}
		}
	}
	
	/**
	 * Mix deck cards
	 */
	public void mix() {
		Random rand = new Random();
		int size = deck.size();
		for (int i = 0; i < size; i++) {
			int j = rand.nextInt(size);
			swapCards(i, j);
		}
	}

	// swap cards
	private void swapCards(int i, int j) {
		Card tempCard = deck.get(i);
		deck.set(i, deck.get(j));
		deck.set(j, tempCard);
	}
	
	/**
	 * 
	 * @return true, if Deck is empty,
	 * else returns false.
	 */
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	/**
	 * 
	 * @return Deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	/**
	 *  returns a String representation of a Deck
	 */
	@Override
	public String toString() {
		int size = deck.size();
		if (size == 0)
			return "Deck is empty.";

		String str1 = "Deck Size: " + size + ", top: " + deck.get(TOP) + ", deck is: \n";
		String str2 = new String("");

		for (int i = 0; i < size; i++) {
			str2 += deck.get(i).toString() + "\n";
		}

		return str1 + str2;
	}
}
