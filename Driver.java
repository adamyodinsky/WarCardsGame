import javax.swing.JOptionPane;

/**
 * Driver Class
 * @author Adam Yodinsky
 */
public class Driver {
	public static void main(String[] args) {
		String name1; 		//name of player1
		String name2; 		//name of player2
		Player winner;		//holds temporary winner at fights
		
		welcome();
		
		// get players names
		
		name1 = JOptionPane.showInputDialog("Please enter name of Player 1");
		name2 = JOptionPane.showInputDialog("Please enter name of Player 2");
		
		//end of settings, starts the game print
		JOptionPane.showMessageDialog(null, "Great! Lets start play!\n"
											+ "Deal cards to players...");
		
		// create a new game
		WarGame game = new WarGame(name1, name2);
		game.dealCards();
		
		//print starting status
		JOptionPane.showMessageDialog(null, "starting status:\n" + game);
//		JOptionPane.showMessageDialog(null, game.getPlayer1() + "  - Vs -  " + game.getPlayer2());
		
		//fight!      
		while (!game.ended()) {
			winner = game.fight(); // fight and save the winner
			winner.winCards(game.getMain()); //winner taking the cards on the table (main Deck)
//			JOptionPane.showMessageDialog(null, winner + " wins the fight!"); 
		}
		
		game.winnerIs(); //print the winner of the game	
	
	}
	
	private static void welcome() {
		JOptionPane.showMessageDialog(null, "Hi, Welcome to Cards War Game!\n");
	}
}	

