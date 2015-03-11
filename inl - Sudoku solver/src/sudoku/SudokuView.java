package sudoku;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

/**
 * Main Application
 * 
 * @author TehSsounD
 *
 */
@SuppressWarnings("serial")
public class SudokuView extends JFrame {
	private SudokuBoard board;
	private SudokuPanel panel;
	private SudokuCommand command;
	private String path;

	/** 
	 * Main metoden som startar applikationen. 
	 * 
	 * @param args argumenten
	 */
	public static void main(String[] args) {
		new SudokuView("Sudoku Solver");
	}

	/** 
	 * Skapar ett JFrame GUI med ett sudokubräde och en kontrollpanel. 
	 * 
	 * @param title fönstrets titel
	 */
	public SudokuView(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		board = new SudokuBoard();
		panel = new SudokuPanel(this);
		command = new SudokuCommand(this);

		add(panel, BorderLayout.CENTER);
		add(command, BorderLayout.SOUTH);

		JMenu sudokuMenu = new JMenu("Sudokus");
		loadFromFile(sudokuMenu);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(sudokuMenu);
		setJMenuBar(menuBar);

		pack();
		setVisible(true);
	}

	/** Tar bort alla värden från alla rutor och uppdaterar sudokupanelen. */
	public void clear() {
		panel.setBoard(board.clear());
	}

	/** Försöker att hitta en lösning och uppdaterar sudokupanelen. */
	public void solve() {
		board.setBoard(getBoard());
		if (board.solve()) {
			panel.setBoard(board.getBoard());
			JOptionPane.showMessageDialog(null, "Sudoku solved!",
					"Sudoku alert", JOptionPane.INFORMATION_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Sudoku unsolvable!",
					"Sudoku alert", JOptionPane.ERROR_MESSAGE);
	}

	/** 
	 * Retunerar heltalsmatrisen som motsvarar sudokupanelens rutnät. 
	 * 
	 * @return sudokuns heltalsmatris
	 */
	public int[][] getBoard() {
		return panel.getBoard();
	}

	/** Läser in filen från platsen path till JMenu menu. */
	private void loadFromFile(JMenu menu) {
		switch(JOptionPane.showConfirmDialog(null,
				"Load from file?")) {
				case 2: System.exit(0);
				case 0: path = JOptionPane.showInputDialog(null, "Load sudokus", "Choose file",
						JOptionPane.QUESTION_MESSAGE);
				if (path != null && path.isEmpty())
					path = null;
		}
		if(path == null) {
			path = "Standard";
			new File("resources/" + path);
		}
		Scanner s = null;
		try {
			s = new Scanner(new File("resources/" + path));
			int i = 1;
			while (s.hasNext()) {
				JMenuItem item = new JMenuItem("Sudoku " + i++);
				int[][] matrix = new int[9][9];
				for (int x = 0; x < 9; x++) {
					for (int y = 0; y < 9; y++)
						matrix[x][y] = s.nextInt();
				}
				item.addActionListener(new MenuListener(matrix));
				menu.add(item);
			}
		} catch (FileNotFoundException e) {
			
		}
	}

	/** Skriver ut filen till platsen path. */
	public void printToFile() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter("resources/"
					+ path)));
			int[][] board = getBoard();
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 9; y++) {
					pw.append(board[x][y] + " ");
				}
				pw.append("\n");
			}
			pw.append("\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Couldn't print!");
		} finally {
			if(pw != null) pw.close();
		}
	}
	
	/** När du interagerar med JMenuItems i sudokuns menyn ska valets fil föra in dess värde till brädet.*/
	private class MenuListener implements ActionListener {
		private int[][] matrix;

		/** Skapar en MenuListener som sparar vårt bräde i vårt attribut. */
		public MenuListener(int[][] matrix) {
			this.matrix = matrix;
		}

		/** Kallar på SudokuPanel's setBoard metod. */
		public void actionPerformed(ActionEvent e) {
			panel.setBoard(matrix);
		}
	}
}
