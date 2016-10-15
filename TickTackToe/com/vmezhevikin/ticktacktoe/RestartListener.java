package com.vmezhevikin.ticktacktoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {

	private Controller controller;
	
	public RestartListener(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.startNewGame();
	}
}