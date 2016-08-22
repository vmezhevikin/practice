package com.vmezhevikin.mines;

public class Controller {
	
	private Field field;
	private Frame frame;
	
	private boolean gameOver;
	
	private final int[] diffR = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private final int[] diffC = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	public Controller() {
		field = new Field();
		frame = new Frame(field, this);
		startGame();
	}
	
	public void startGame() {
		field.clearField();
		field.putMines();
		gameOver = false;
		frame.prepareView();
	}
	
	public void nextTurn() {
		frame.refreshView();
	}
	
	public void finishGameWithMessageGameOver() {
		gameOver = true;
		frame.showViewAtGameEndWithMessageGameOver();
	}
	
	public void finishGameWithMessageYouWon() {
		gameOver = true;
		frame.showViewAtGameEndWithMessageYouWon();
	}
	
	public boolean gameOver() {
		return gameOver;
	}
	
	public void openCell(int row, int col) {
		if (!field.cellInField(row, col)) {
			return;
		}
		if (field.cellIsOpenned(row, col) || field.cellIsMarkedAsMine(row, col) || field.cellIsMarkedAsNotSure(row, col)) {
			return;
		}
		field.openCell(row, col);
		if (field.cellHasMine(row, col)) {
			finishGameWithMessageGameOver();
			return;
		}
		if (field.getMinesNearCell(row, col) == 0) {
			for (int i = 0; i < diffR.length; i++) {
				openCell(row + diffR[i], col + diffC[i]);
			}
		}
		if (field.getNeedToOpenCellCount() == 0) {
			finishGameWithMessageYouWon();
		} else {
			nextTurn();
		}
	}
	
	public void markCellAsMine(int row, int col) {
		if (!field.cellInField(row, col)) {
			return;
		}
		if (field.cellIsOpenned(row, col) || field.cellIsMarkedAsNotSure(row, col)) {
			return;
		}
		if (field.cellIsMarkedAsMine(row, col)) {
			field.unmarkCellAsMine(row, col);
		} else {
			if (field.getMarkedMinesCount() > 0) {
				field.markCellAsMine(row, col);
			}
		}
		nextTurn();
	}
	
	public void markCellAsNotSure(int row, int col) {
		if (!field.cellInField(row, col)) {
			return;
		}
		if (field.cellIsOpenned(row, col) || field.cellIsMarkedAsMine(row, col)) {
			return;
		}
		if (field.cellIsMarkedAsNotSure(row, col)) {
			field.unmarkCellAsQuestion(row, col);
		} else {
			field.markCellAsQuestion(row, col);
		}
		nextTurn();
	}
}
