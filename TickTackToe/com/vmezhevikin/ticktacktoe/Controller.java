package com.vmezhevikin.ticktacktoe;

import static com.vmezhevikin.ticktacktoe.Constants.*;

import java.util.Random;

public class Controller {
	
	private static final Random RAND = new Random();
	
	private static final int[][] DELTA = { {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1} };

	private Field field;
	private Frame frame;
	
	private int gameOver;
	private int turn;
	
	public Controller() {
		field = new Field();
		frame = new Frame(field, this);
		gameOver = GAME_OVER;
	}

	public void startNewGame() {
		field.clearField();
		frame.showClearMenu();
		frame.showNewFieldView();
		gameOver = GAME_CONTINUE;
		turn = setFirstSide();
		if (turn == COMP_TURN) {
			makeFirstComputerTurn();
		}
		frame.showNewFieldView();
	}
	
	private void makeFirstComputerTurn() {
		int row = RAND.nextInt(FIELD_ROWS / 3) + FIELD_ROWS / 3;
		int col = RAND.nextInt(FIELD_COLS / 3) + FIELD_ROWS / 3;
		field.markByComputer(row, col);
		changeTurn();
	}

	public boolean gameOver() {
		return gameOver == GAME_OVER;
	}

	public void makeUserMoveToCell(int row, int col) {
		if (!field.isEmpty(row, col)) {
			return;
		}
		field.markByUser(row, col);
		frame.showNewFieldView();
		int gameOverResult = analizeIfGameOver();
		if (gameOverResult == GAME_CONTINUE) {
			changeTurn();
			makeComputerTurn();
		} else if (gameOverResult == GAME_OVER) {
			gameOver = GAME_OVER;
			frame.showMenuMessageUserWin();
		} else {
			gameOver = GAME_OVER;
			frame.showMenuMessageDraw();
		}
	}
	
	public boolean isUserTurn() {
		return turn == USER_TURN;
	}

	private int setFirstSide() {
		if (RAND.nextBoolean()) {
			return USER_TURN;
		} else {
			return COMP_TURN;
		}
	}
	
	private int analizeIfGameOver() {
		int empties = 0;
		for (int row = 0; row < FIELD_ROWS; row++) {
			for (int col = 0; col < FIELD_COLS; col++) {
				if (field.isEmpty(row, col)) {
					empties++;
					continue;
				}
				int type = field.getMark(row, col);
				for (int i = 0; i < DELTA.length; i++) {
					int count = 1;
					for (int k = 1; k < 5; k++) {
						int r = row + DELTA[i][0] * k;
						int c = col + DELTA[i][1] * k;
						if (field.isOutOfField(r, c)) {
							continue;
						}
						if (field.getMark(r, c) == type) {
							count++;
						}
					}
					if (count == 5) {
						return GAME_OVER;
					}
				}
			}
		}
		if (empties == 0) {
			return GAME_DRAW;
		}
		return GAME_CONTINUE;
	}
	
	private void changeTurn() {
		if (turn == USER_TURN) {
			turn = COMP_TURN;
		} else {
			turn = USER_TURN;
		}
	}
	
	private void makeComputerTurn() {
		int markResult = markCellInLineOfNCellsOfType(5, CELL_COMP_MARK);
		if (markResult == CELL_NOTFOUND) {
			markResult = markCellInLineOfNCellsOfType(5, CELL_USER_MARK);
		}		
		if (markResult == CELL_NOTFOUND) {
			markResult = markCellInLineOfNCellsOfType(4, CELL_COMP_MARK);
		}	
		if (markResult == CELL_NOTFOUND) {
			markResult = markCellInLineOfNCellsOfType(4, CELL_USER_MARK);
		}
		if (markResult == CELL_NOTFOUND) {
			markResult = markCellInLineOfNCellsOfType(3, CELL_COMP_MARK);
		}
		if (markResult == CELL_NOTFOUND) {
			markResult = markCellInLineOfNCellsOfType(2, CELL_COMP_MARK);
		}
		if (markResult == CELL_NOTFOUND) {
			markResult = markRandomEmptyCell();
		}
		frame.showNewFieldView();
		int gameOverResult = analizeIfGameOver();
		if (gameOverResult == GAME_CONTINUE) {
			changeTurn();
		} else if (gameOverResult == GAME_OVER) {
			gameOver = GAME_OVER;
			frame.showMenuMessageComputerWin();
		} else {
			gameOver = GAME_OVER;
			frame.showMenuMessageDraw();
		}
	}
	
	private int markCellInLineOfNCellsOfType(int number, int type) {
		for (int row = 0; row < FIELD_ROWS; row++) {
			for (int col = 0; col < FIELD_COLS; col++) {
				if (field.getMark(row, col) != type) {
					continue;
				}
				for (int i = 0; i < DELTA.length; i++) {
					int count = 1;
					for (int k = 1; k < number; k++) {
						int r = row + DELTA[i][0] * k;
						int c = col + DELTA[i][1] * k;
						if (field.isOutOfField(r, c)) {
							continue;
						}
						if (field.getMark(r, c) == type) {
							count++;
						}
					}
					boolean promising = true;
					for (int k = 1; k < 5; k++) {
						int r = row + DELTA[i][0] * k;
						int c = col + DELTA[i][1] * k;
						if (field.isOutOfField(r, c)) {
							promising = false;
							continue;
						}
						if (field.isEmpty(r, c)) {
							continue;
						}
						if (field.getMark(r, c) == opponentTo(type)) {
							promising = false;
						}
					}
					if (count == number - 1 && promising) {
						for (int k = 1; k < number; k++) {
							int r = row + DELTA[i][0] * k;
							int c = col + DELTA[i][1] * k;
							if (field.isOutOfField(r, c)) {
								continue;
							}
							if (field.getMark(r, c) == CELL_EMPTY) {
								field.markByComputer(r, c);
								return CELL_FOUND;
							}
						}
					}
				}
			}
		}
		return CELL_NOTFOUND;
	}

	private int markRandomEmptyCell() {
		for (int i = 0; i < 25; i++) {
			int row = RAND.nextInt(FIELD_ROWS);
			int col = RAND.nextInt(FIELD_COLS);
			if (field.isEmpty(row, col)) {
				field.markByComputer(row, col);
				return CELL_FOUND;
			}
		}
		for (int row = 0; row < FIELD_ROWS; row++) {
			for (int col = 0; col < FIELD_COLS; col++) {
				if (field.isEmpty(row, col)) {
					field.markByComputer(row, col);
					return CELL_FOUND;
				}
			}
		}
		return CELL_NOTFOUND;
	}
	
	private int opponentTo(int type) {
		if (type == CELL_COMP_MARK) {
			return CELL_USER_MARK;
		}
		if (type == CELL_USER_MARK) {
			return CELL_COMP_MARK;
		}
		return CELL_EMPTY;
	}
}