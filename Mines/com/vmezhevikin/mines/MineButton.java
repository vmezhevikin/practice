package com.vmezhevikin.mines;

import javax.swing.JButton;

public class MineButton extends JButton {

	private static final long serialVersionUID = -2146473572446430952L;
	
	private int row;
	private int column;

	public MineButton(int row, int col) {
		super();
		this.row = row;
		this.column = col;
		setBounds(row, col);
		setFont(Frame.BUTTON_FONT);
	}
	
	private void setBounds(int row, int col) {
		int adjPosX = col * Frame.BUTTON_SIZE + Frame.PADDING;
		int adjPosY = row * Frame.BUTTON_SIZE + Frame.PADDING + Frame.LABEL_HEIGHT;
		int width = Frame.BUTTON_SIZE - 2 * Frame.PADDING;
		int height = Frame.BUTTON_SIZE - 2 * Frame.PADDING;
		setBounds(adjPosX, adjPosY, width, height);
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
