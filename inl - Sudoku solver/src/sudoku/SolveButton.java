package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SolveButton extends JButton implements ActionListener {

	private SudokuView view;

	/** 
	 * Skapar en solve knapp som sparar vår SudokuView i vårt attribut. 
	 * 
	 * @param view SudokuView som används
	 */
	public SolveButton(SudokuView view) {
		super("Solve");
		this.view = view;
		addActionListener(this);
		this.setToolTipText("Tries to solve the sudoku");
	}

	@Override
	/** Kallar på SudokuView's solve metod. */
	public void actionPerformed(ActionEvent e) {
		view.solve();
	}
}
