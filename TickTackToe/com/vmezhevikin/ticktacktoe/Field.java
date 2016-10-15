package com.vmezhevikin.ticktacktoe;

import static com.vmezhevikin.ticktacktoe.Constants.*;

public class Field {
	
	private int[][] field = new int[FIELD_ROWS][FIELD_COLS];
	
	public Field() {
		clearField();
	}
	
	public void clearField() {
		for (int row = 0; row < FIELD_ROWS; row++) {
			for (int col = 0; col < FIELD_COLS; col++) {
				field[row][col] = CELL_EMPTY;
			}
		}
	}

	public boolean isEmpty(int row, int col) {
		return field[row][col] == CELL_EMPTY;
	}

	public boolean markedByComputer(int row, int col) {
		return field[row][col] == CELL_COMP_MARK;
	}

	public boolean markedByUser(int row, int col) {
		return field[row][col] == CELL_USER_MARK;
	}

	public void markByComputer(int row, int col) {
		field[row][col] = CELL_COMP_MARK;
	}

	public void markByUser(int row, int col) {
		field[row][col] = CELL_USER_MARK;
	}

	public int getMark(int row, int col) {
		return field[row][col];
	}

	public boolean isOutOfField(int row, int col) {
		if (row < 0 || row >= FIELD_ROWS || col < 0 || col >= FIELD_COLS) {
			return true;
		}
		return false;
	}
}