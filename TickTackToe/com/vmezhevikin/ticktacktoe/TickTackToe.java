package com.vmezhevikin.ticktacktoe;

import java.awt.EventQueue;

public class TickTackToe {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Controller();
			}
		});
	}
}