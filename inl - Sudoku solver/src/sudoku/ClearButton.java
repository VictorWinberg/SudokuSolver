package sudoku;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ClearButton extends JButton {

	private SudokuView view;
	
	/** 
	 * Skapar en clear knapp som sparar vår SudokuView i vårt attribut. 
	 * 
	 * @param view SudokuView som används
	 */
	public ClearButton(SudokuView view) {
		super("Clear");
		this.view = view;
		this.setToolTipText("Clears the board");
	}
}
