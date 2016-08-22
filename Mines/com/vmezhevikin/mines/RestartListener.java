package com.vmezhevikin.mines;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {

	private Controller logicBlock;
	
	public RestartListener(Controller logicBlock) {
		this.logicBlock = logicBlock;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		logicBlock.startGame();
	}
}
