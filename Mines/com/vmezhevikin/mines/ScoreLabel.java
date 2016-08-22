package com.vmezhevikin.mines;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ScoreLabel extends JLabel {
	
	private static final long serialVersionUID = 8671558278159446013L;

	public ScoreLabel(int posX, int posY) {
		super();
		setBounds(posX, posY);
		setFont(Frame.LABEL_FONT);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	private void setBounds(int posX, int posY) {
		int adjPosX = posX + Frame.PADDING;
		int adjPosY = posY + Frame.PADDING;
		int width = Frame.LABEL_WIDTH - 2 * Frame.PADDING;
		int height = Frame.LABEL_HEIGHT - 2 * Frame.PADDING;
		setBounds(adjPosX, adjPosY, width, height);
	}
	
	public void setText(int mines) {
		setText(mines + "/" + Constants.TOTAL_MINES);
	}
}
