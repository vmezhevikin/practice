package com.vmezhevikin.ticktacktoe;

import javax.swing.JButton;

import static com.vmezhevikin.ticktacktoe.Constants.*;

@SuppressWarnings("serial")
public class RestartButton extends JButton {

	public RestartButton(int posX, int posY) {
		super();
		setBounds(posX, posY);
		setFont(BUTTON_FONT);
		setBorder(BUTTON_BORDER);
		setBackground(CELL_EMPTY_COLOR);
	}
	
	private void setBounds(int posX, int posY) {
		int adjPosX = posX + PADDING;
		int adjPosY = posY + PADDING;
		int width = LABEL_WIDTH - 2 * PADDING;
		int height = LABEL_HEIGHT - 2 * PADDING;
		setBounds(adjPosX, adjPosY, width, height);
	}
}