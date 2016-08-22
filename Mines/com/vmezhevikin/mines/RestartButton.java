package com.vmezhevikin.mines;

import javax.swing.JButton;

public class RestartButton extends JButton {

	private static final long serialVersionUID = 5656712416256091146L;

	public RestartButton(int posX, int posY) {
		super();
		setBounds(posX, posY);
		setFont(Frame.LABEL_FONT);
	}
	
	private void setBounds(int posX, int posY) {
		int adjPosX = posX + Frame.PADDING;
		int adjPosY = posY + Frame.PADDING;
		int width = Frame.LABEL_WIDTH - 2 * Frame.PADDING;
		int height = Frame.LABEL_HEIGHT - 2 * Frame.PADDING;
		setBounds(adjPosX, adjPosY, width, height);
	}
}
