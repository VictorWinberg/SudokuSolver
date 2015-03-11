package sudoku;

import java.awt.FlowLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SudokuCommand extends JPanel{
	
	/** Skapar en kontrolpanel. 
	 * 
	 * @param view SudokuView som används
	 */
	public SudokuCommand(SudokuView view) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new ClearButton(view));
		add(new SolveButton(view));
		add(new PrintButton(view));
	}
}
