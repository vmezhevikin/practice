package com.vmezhevikin.mines;

import java.util.Random;

import static com.vmezhevikin.mines.Constants.*;

public class Field {

	public static final boolean MINE = true;
	public static final boolean EMPTY = false;

	public static final int CLOSED = 0;
	public static final int MARKED_AS_MINE = 1;
	public static final int MARKED_AS_QUESTION = 2;
	public static final int OPENNED = 3;

	private boolean[][] mines = new boolean[ROWS][COLS];
	private int[][] state = new int[ROWS][COLS];
	private int[][] minesNear = new int[ROWS][COLS];
	
	private int markedMinesCount;
	private int needToOpenCellCount;
	
	private int lastOpennedCellRow;
	private int lastOpennedCellCol;
	
	private Random random = new Random();
	
	private final int[] diffR = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private final int[] diffC = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	public void clearField() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				mines[row][col] = EMPTY;
				state[row][col] = CLOSED;
				minesNear[row][col] = 0;
			}
		}
		markedMinesCount = 0;
		needToOpenCellCount = 0;
	}
	
	public void putMines() {
		generateMines();
		countMinesNear();
	}
	
	private void generateMines() {
		int count = 0;
		while (count < TOTAL_MINES) {
			int row = random.nextInt(ROWS);
			int col = random.nextInt(COLS);
			if (mines[row][col] == EMPTY) {
				mines[row][col] = MINE;
				count++;
			}
		}
		markedMinesCount = TOTAL_MINES;
		needToOpenCellCount = ROWS * COLS - TOTAL_MINES;
	}
	
	private void countMinesNear() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				int count = 0;
				for (int i = 0; i < diffR.length; i++) {
					if (!cellInField(row + diffR[i], col + diffC[i])) {
						continue;
					}
					if (mines[row + diffR[i]][col + diffC[i]] == MINE) {
						count++;
					}
				}
				minesNear[row][col] = count;
			}
		}
	}
	
	public boolean cellInField(int row, int col) {
		if (row < 0 || row >= ROWS) {
			return false;
		}
		if (col < 0 || col >= COLS) {
			return false;
		}
		return true;
	}
	
	public boolean cellHasMine(int row, int col) {
		return mines[row][col] == MINE;
	}
	
	public void openCell(int row, int col) {
		lastOpennedCellRow = row;
		lastOpennedCellCol = col;
		state[row][col] = OPENNED;
		needToOpenCellCount--;
	}
	
	public boolean cellIsOpenned(int row, int col) {
		return state[row][col] == OPENNED;
	}
	
	public boolean cellIsClosed(int row, int col) {
		return state[row][col] == CLOSED;
	}
	
	public void markCellAsMine(int row, int col) {
		state[row][col] = MARKED_AS_MINE;
		markedMinesCount--;
	}
	
	public void unmarkCellAsMine(int row, int col) {
		state[row][col] = CLOSED;
		markedMinesCount++;
	}
	
	public boolean cellIsMarkedAsMine(int row, int col) {
		return state[row][col] == MARKED_AS_MINE;
	}
	
	public void markCellAsQuestion(int row, int col) {
		state[row][col] = MARKED_AS_QUESTION;
	}
	
	public void unmarkCellAsQuestion(int row, int col) {
		state[row][col] = CLOSED;
	}
	
	public boolean cellIsMarkedAsNotSure(int row, int col) {
		return state[row][col] == MARKED_AS_QUESTION;
	}
	
	public int getMinesNearCell(int row, int col) {
		return minesNear[row][col];
	}
	
	public int getMarkedMinesCount() {
		return markedMinesCount;
	}

	public int getNeedToOpenCellCount() {
		return needToOpenCellCount;
	}

	public int getLastOpennedCellRow() {
		return lastOpennedCellRow;
	}

	public int getLastOpennedCellCol() {
		return lastOpennedCellCol;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (cellHasMine(row, col)) {
					res.append("X");
				} else {
					res.append(getMinesNearCell(row, col));
				}
			}
			res.append("\n");
		}
		return res.toString();
	}
}
