package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ClearButton extends JButton implements ActionListener {

	private SudokuView view;
	
	/** 
	 * Skapar en clear knapp som sparar vår SudokuView i vårt attribut. 
	 * 
	 * @param view SudokuView som används
	 */
	public ClearButton(SudokuView view) {
		super("Clear");
		this.view = view;
		addActionListener(this);
		this.setToolTipText("Clears the board");
	}
	
	@Override
	/** Kallar på SudokuView's clear metod. */
	public void actionPerformed(ActionEvent e) {
		view.clear();
	}
}
