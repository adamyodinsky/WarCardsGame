import javax.swing.JOptionPane;

/**
 * Class WarGame represents a war card game.
 * @version 290318
 * @author Adam Yodinsky
 */
public class WarGame {

	private Player player1;
	private Player player2;
	private static int numOfRounds;
	private Deck main;

	/**
	 * Constructs a WarGame with a Full new main Deck, 2 Players and a
	 * rounds-counter.
	 * 
	 * @param name1
	 *            - name given for player1
	 * @param name2
	 *            - name given for player2
	 */
	public WarGame(String name1, String name2) {
		this.player1 = new Player(name1);
		this.player2 = new Player(name2);
		main = new Deck();
		main.initializeDeck();
		numOfRounds = 0;
	}

	/**
	 * deal 52 full range mixed cards to players
	 */
	public void dealCards() {
		main.mix();

		// deal all cards to players decks
		while (!main.getDeck().isEmpty()) {
			player1.getCurr().getDeck().add(main.getDeck().remove(0));
			player2.getCurr().getDeck().add(main.getDeck().remove(0));
		}
	}

	/**
	 * this method shows last Card of player1 entered to main Deck.
	 * 
	 * @return last card player1 entered to main deck
	 */
	private Card p1card() {
		int size = main.getDeck().size();
		if (main.getDeck().size() < 2) {
			JOptionPane.showMessageDialog(null, player1 + " have no cards left.");
			return null;
		}
		return main.getDeck().get(size - 2);
	}

	/**
	 * this method shows last Card of player2 entered to main Deck.
	 * 
	 * @return last card player2 entered to main deck
	 */
	private Card p2card() {
		int size = main.getDeck().size();
		if (main.isEmpty()) {
			JOptionPane.showMessageDialog(null, player2 + " have no cards left.");
			return null;
		}
		return this.main.getDeck().get(size - 1);
	}

	/**
	 * @return the winning player of fight, can enter to war-mode in case of a
	 *         tie.
	 */
	public Player fight() {
		numOfRounds++;

		if (!player1.outOfCards())
			main.getDeck().add(player1.drawCard());
		else
			return player2; // if player1 lost so return winner player2

		if (!player2.outOfCards())
			main.getDeck().add(player2.drawCard());
		else
			return player1; // if player2 lost so return winner player1

		// fight
		if (p1card().getVal() > p2card().getVal()) {
			putFight(player1, p1card(), p2card());
			return player1;
		} else if (p1card().getVal() < p2card().getVal()) {
			putFight(player2, p1card(), p2card());
			return player2;
		} else { // if there is a tie
			putFight(p1card(), p2card());
			return warMode(""); // go to warMode
		}
	}
	/**
	 * 
	 * @param p1card - player1 card
	 * @param p2card - player2 card
	 * @param war - String of war history
	 * @return Player who wan the war
	 */
	public Player fight(Card p1card, Card p2card, String war) {

		if (player1.outOfCards()) {
			putFight(war, player2, p1card, p2card); 
			return player2; // if player1 lost so return winner player2
		}
		if (player2.outOfCards()) {
			putFight(war, player1, p1card, p2card);
			return player1; // if player2 lost so return winner player1
		}

		// fight
		numOfRounds++;
		if (p1card.getVal() > p2card.getVal()) {
			putFight(war, player1, p1card, p2card);
			return player1;
		} else if (p1card.getVal() < p2card.getVal()) {
			putFight(war, player2, p1card, p2card);
			return player2;
		} else { // if there is a tie
			if (player1.outOfCards()) {
				putFight(war, player2, p1card, p2card);
				return player2;
			}
			if (player2.outOfCards()) {
				putFight(war, player1, p1card, p2card);
				return player1;
			}
			return warMode(war);
		}
	}

	// returns winning players after a war
	private Player warMode(String war) {
		Card p1card = null;
		Card p2card = null;
		war += "***WAR-MODE***\n";

		for (int i = 0; i < 3; i++) { // draw 3 times or less if sides out of cards
			if (this.ended())
				break; // draw only if both sides have cards left at all

			p1card = player1.drawCard(); // draw cards
			p2card = player2.drawCard();

			war += strfight(p1card, p2card) + "\n"; // string of war

			main.getDeck().add(p1card); // add to main deck
			main.getDeck().add(p2card); // add to main deck
		}
		
		// call to fight to return winner of war
		return fight(p1card, p2card, war); 
	}

	/**
	 * print on a box a fight "player1 card - vs - player2 card"
	 * @param p1card - player1 Card
	 * @param p2card - Player2 Card
	 */
	private void putFight(Card p1card, Card p2card) {
		// print fight
		JOptionPane.showMessageDialog(null, p1card + " -Vs- " + p2card + "   This is a Tie!");
	}

	/**
	 * return a string "player1 card - vs - player2 card"
	 * @param p1card - player1 Card
	 * @param p2card - Player2 card
	 * @return String represents a fight
	 */
	private String strfight(Card p1card, Card p2card) {
		// print fight
		return p1card + " -Vs- " + p2card;
	}

	/**
	 * @return true - if game has ended.
	 */
	public boolean ended() {
		if (player1.outOfCards() || player2.outOfCards())
			return true;
		return false;
	}

	/**
	 * @return Player object who wan the WarGame. 
	 * if there is no winner return null.
	 */
	private Player winner() {
		if (player1.outOfCards()) {
			JOptionPane.showMessageDialog(null, player1 + " is Out of Cards!");
			return player2;
		} if (player2.outOfCards()) {
			JOptionPane.showMessageDialog(null, player2 + " is Out of Cards!");
			return player1;
		}
		return null;
	}

	/**
	 * prints the winner in the game 
	 */
	public void winnerIs() {
		JOptionPane.showMessageDialog(null,
				"The Ultimate Winner in the WarGame is: " + winner() +  
				"\nNumber of Rounds: " + numOfRounds);
	}

	public Deck getMain() {
		return main;
	}

	private void putFight(String war, Player player, Card p1card, Card p2card) {
		JOptionPane.showMessageDialog(null, war + player + " Wins The War!");
	}

	private void putFight(Player player, Card p1card, Card p2card) {
		JOptionPane.showMessageDialog(null, p1card + " -Vs- " + p2card + "\n" + player + " Wins The Fight!");
	}

	/**
	 * returns a String representation of a the game status
	 */
	@Override
	public String toString() {
		return player1 + " have " + player1.howManyCards() + " cards\n" + player2 + " have " + player2.howManyCards()
				+ " cards.";
	}

}
