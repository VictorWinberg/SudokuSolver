package sudoku;

public class SudokuBoard {
	// En heltalsmatris, motsvarande sudokuns rutnät.
	private int[][] board;

	/** Skapar ett tomt sudokubräde. */
	public SudokuBoard() {
		board = new int[9][9];
	}

	/** 
	 * Sätter värdet value i rutan (row, col). 
	 * 
	 * @param value värdet
	 * @param row rad
	 * @param col kolomn
	 */
	public void put(int value, int row, int col) {
		if(value > 9 || value < 0)
			throw new IllegalArgumentException("Felaktigt värde " + value);
		board[row][col] = value;
	}

	/** 
	 * Retunerar värdet i rutan (row, col), noll om rutan är tom. 
	 * 
	 * @param row rad
	 * @param col kolomn
	 * @return retunerar värdet i rutan (row, col), noll om rutan är tom. 
	 */
	public int get(int row, int col) {
		return board[row][col];
	}

	/** 
	 * Försöker att hitta en lösning. Detta görs rekursivt med s.k.
	 * backtrackingteknik.
	 * 
	 * @return true om lösning hittades, annars false 
	 */
	public boolean solve() {
		return checkBoard(0,0) && solve(0,0);
	}
	
	/** 
	 * Går igenom brädet och kontrollerar att alla värden är tillåtna. 
	 * 
	 * @param row rad
	 * @param col kolomn
	 * @return true om alla redan befintliga värden i brädet är tillåtna att ha där. Annars false.
	 */
	private boolean checkBoard(int row, int col) {
		if(board[row][col] != 0) {
			if(!allowed(board[row][col], row, col))
				return false;
		}
		return findNext(row, col);
	}
	
	/** 
	 * Letar upp nästa ruta och kallar på check. 
	 * 
	 * @param row rad
	 * @param col kolomn
	 * @return true om det finns ett okej värde att stoppa in i nästa ruta.
     */
	private boolean findNext(int row, int col) {
		if(row == 8 && col == 8)
			return true;
		if(col < 8)
			return checkBoard(row, col + 1);
		else
			return checkBoard(row + 1, 0);
	}

	/** 
	 * Rekursiv backtrackingsmetod 
	 * 
	 * @param row rad
	 * @param col kolomn
	 * @return true om sudokut går att lösa, annars false.
     */
	private boolean solve(int row, int col) {
		if(board[row][col] == 0) {
			for(int i = 1; i <= 9; i++) {
				if(allowed(i, row, col)){
					board[row][col] = i;
					if(solveNext(row, col))
						return true;
				}
			}
			board[row][col] = 0;
			return false;
		}
		else if(allowed(board[row][col], row, col)) {
			return solveNext(row, col);
		}
		return false;
	}
	
	/** 
	 * Letar upp nästa ruta och kallar på solve 
	 * 
	 * @param row rad
	 * @param col kolomn
	 * @return true om nästa rut går att lösa, annars false.
     */
	private boolean solveNext(int row, int col) {
		if(row == 8 && col == 8)
			return true;
		if(col < 8)
			return solve(row, col + 1);
		else
			return solve(row + 1, 0);
	}
	
	/** 
	 * Kontrollerar om värdet i rutan (row, col) uppfyller sudokuns krav 
	 * 
	 * @param value värdet
	 * @param row rad
	 * @param col kolomn
	 * @return true om value är ett okej värde att stoppa in i rutan enligt reglerna, annars false
     */
	public boolean allowed(int value, int row, int col) {
		// Kontrollerar värdet med raden
		for(int i = 0; i < 9; i++) {
			int temp = board[row][i];
			if(temp == value && i != col)
				return false;
		}
		// Kontrollerar värdet med kolomnen
		for(int i = 0; i < 9; i++) {
			int temp = board[i][col];
			if(temp == value & i != row)
				return false;
		}
		// Kontrollerar värdet med 3x3 rutan
		int irow = (row / 3) * 3;
		int icol = (col / 3) * 3;
		for(int x = irow; x < irow + 3; x++) {
			for(int y = icol; y < icol + 3; y++) {
				if(x != row && y != col && board[x][y] == value)
					return false;
			}
		}
		return true;
	}

	/** Tar bort alla värden från alla rutor och retunerar det tomma brädet. 
	 * 
	 * @return retunerar en tom heltalsmatris
	 */
	public int[][] clear() {
		return board = new int[9][9];
	}
	
	/** 
	 * Sätter heltalsmatrisen board till sudokuns nya rutnät. 
	 * 
	 * @param board heltalsmatrisen
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	/** 
	 * Retunerar heltalsmatrisen som motsvarar sudokuns rutnät. 
	 * 
	 * @return retunerar heltalsmatrisen som motsvarar sudokuns rutnät. 
	 */
	public int[][] getBoard() {
		return board;
	}
}
