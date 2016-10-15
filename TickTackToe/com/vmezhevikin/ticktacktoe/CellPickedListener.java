package com.vmezhevikin.ticktacktoe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class CellPickedListener implements MouseListener {
	
	private Controller controller;
	
	public CellPickedListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (!controller.gameOver() && controller.isUserTurn()) {
			responseOnEvent(event);
		}
	}
	
	private void responseOnEvent(MouseEvent event) {
		Object actionObject = event.getSource();
		if (actionObject instanceof CellButton) {
			CellButton button = (CellButton) actionObject;
			int row = button.getRow();
			int col = button.getColumn();
			performActionWithCell(event, row, col);
		}
	}
	
	private void performActionWithCell(MouseEvent event, int row, int col) {
		if (SwingUtilities.isLeftMouseButton(event)) {
			controller.makeUserMoveToCell(row, col);
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// not used
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// not used
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// not used
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// not used
	}
}