package com.vmezhevikin.ticktacktoe;

import javax.swing.JButton;

import static com.vmezhevikin.ticktacktoe.Constants.*;

@SuppressWarnings("serial")
public class CellButton extends JButton {

	private int row;
	private int column;

	public CellButton(int row, int col) {
		super();
		this.row = row;
		this.column = col;
		setBounds(row, col);
		setFont(BUTTON_FONT);
		setBorder(BUTTON_BORDER);
		setBackground(CELL_EMPTY_COLOR);
	}
	
	private void setBounds(int row, int col) {
		int adjPosX = col * BUTTON_SIZE + PADDING;
		int adjPosY = row * BUTTON_SIZE + PADDING + LABEL_HEIGHT;
		int width = BUTTON_SIZE - 2 * PADDING;
		int height = BUTTON_SIZE - 2 * PADDING;
		setBounds(adjPosX, adjPosY, width, height);
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}