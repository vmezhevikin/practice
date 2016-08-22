package com.vmezhevikin.mines;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class MinePickedListener implements MouseListener {
	
	private Controller controller;
	
	public MinePickedListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (!controller.gameOver()) {
			reactOnEvent(event);
		}
	}
	
	private void reactOnEvent(MouseEvent event) {
		Object actionObject = event.getSource();
		if (actionObject instanceof MineButton) {
			MineButton button = (MineButton) actionObject;
			int row = button.getRow();
			int col = button.getColumn();
			performActionWithCell(event, row, col);
		}
	}
	
	private void performActionWithCell(MouseEvent event, int row, int col) {
		if (SwingUtilities.isLeftMouseButton(event)) {
			controller.openCell(row, col);
		} else if (SwingUtilities.isRightMouseButton(event)) {
			controller.markCellAsMine(row, col);
		} else if (SwingUtilities.isMiddleMouseButton(event)) {
			controller.markCellAsNotSure(row, col);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// not used
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// not used
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// not used
	}
}
