package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class PrintButton extends JButton implements ActionListener {

	private SudokuView view;

	/** Skapar en print knapp som sparar vår SudokuView i vårt attribut. 
	 * 
	 * @param view SudokuView som används
	 */
	public PrintButton(SudokuView view) {
		super("Print");
		this.view = view;
		addActionListener(this);
		this.setToolTipText("Saves your sudoku");
	}

	@Override
	/** Kallar på SudokuView's print metod. */
	public void actionPerformed(ActionEvent a) {
		view.printToFile();
		JOptionPane.showMessageDialog(null, "Sudoku saved!",
				"Sudoku alert", JOptionPane.INFORMATION_MESSAGE);
	}
}
