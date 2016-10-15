package com.vmezhevikin.ticktacktoe;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static com.vmezhevikin.ticktacktoe.Constants.*;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private Field field;
	private RestartListener restartListener;
	private CellPickedListener cellPickedListener;
	private RestartButton restartButton;
	private CellButton[][] cellButton;
	
	public Frame(Field field, Controller controller) {
		super();
		this.field = field;
		this.restartListener = new RestartListener(controller);
		this.cellPickedListener = new CellPickedListener(controller);
		setTitle("TickTackToe_v1.0");
		setResizable(false);
		setBounds(150, 100, 450, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addPanel();
	}

	private void addPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		addRestartButton(panel);
		addCellButtons(panel);
		setContentPane(panel);
	}

	private void addRestartButton(JPanel panel) {
		restartButton = new RestartButton(0, 0);
		restartButton.setText("Start new game");
		restartButton.addActionListener(restartListener);
		panel.add(restartButton);
	}

	private void addCellButtons(JPanel panel) {
		cellButton = new CellButton[FIELD_ROWS][FIELD_COLS];
		for (int row = 0; row < FIELD_ROWS; row++) {
			for (int col = 0; col < FIELD_COLS; col++) {
				cellButton[row][col] = new CellButton(row, col);
				cellButton[row][col].addMouseListener(cellPickedListener);
				panel.add(cellButton[row][col]);
			}
		}
	}
	
	public void showNewFieldView() {
		for (int row = 0; row < FIELD_ROWS; row++) {
			for (int col = 0; col < FIELD_COLS; col++) {
				if (field.isEmpty(row, col)) {
					cellButton[row][col].setBackground(CELL_EMPTY_COLOR);
				}
				if (field.markedByComputer(row, col)) {
					cellButton[row][col].setBackground(CELL_COMP_COLOR);
				}
				if (field.markedByUser(row, col)) {
					cellButton[row][col].setBackground(CELL_USER_COLOR);
				}
			}
		}
	}

	public void showClearMenu() {
		restartButton.setText("Start new game");
	}
	
	public void showMenuMessageUserWin() {
		restartButton.setText("Game over. User wins. Start another game");
	}
	
	public void showMenuMessageComputerWin() {
		restartButton.setText("Game over. Computer wins. Start another game");
	}

	public void showMenuMessageDraw() {
		restartButton.setText("Game over. Draw. Start another game");
	}
}