package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {

	private NumberField[][] numbers;

	/** 
	 * Skapar ett sudokubräde. 
	 * 
	 * @param view SudokuView som används
	 */
	public SudokuPanel(SudokuView view) {
		numbers = new NumberField[9][9];
		createGrid();
	}

	/** Ändrar sudokubrädets standardstorlek. */
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	/** Skapar rutnätet. */
	private void createGrid() {
		setLayout(new GridLayout(9, 9));
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				// Hittar 3x3 rutor som finns på jämn rad och kolomn samt mittrutan
				if ((x / 3) * 3 % 2 == 0 && (y / 3) * 3 % 2 == 0
						|| (x / 3) * 3 == (y / 3) * 3)
					numbers[x][y] = new NumberField(Color.PINK);
				else
					numbers[x][y] = new NumberField(Color.MAGENTA);
				add(numbers[x][y]);
			}
		}
	}

	/** 
	 * Sätter heltalsmatrisen board till sudokubrädets nya rutnät. 
	 * 
	 * @param board heltalsmatris
	 */
	public void setBoard(int[][] board) {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (board[x][y] == 0)
					numbers[x][y].setText("");
				else
					numbers[x][y].setText(board[x][y] + "");
			}
		}
	}

	/** 
	 * Retunerar heltalsmatrisen som motsvarar sudokubrädets rutnät. 
	 * 
	 * @return heltalsmatrisen som har sudokuns värden
	 */
	public int[][] getBoard() {
		int[][] board = new int[9][9];
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (numbers[x][y].getText().isEmpty())
					board[x][y] = 0;
				else
					board[x][y] = Integer.parseInt(numbers[x][y].getText());
			}
		}
		return board;
	}
}
