package com.vmezhevikin.mines;

import java.awt.EventQueue;

public class Mines {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Controller();
			}
		});
	}
}
