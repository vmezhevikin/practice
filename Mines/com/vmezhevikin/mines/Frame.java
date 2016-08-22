package com.vmezhevikin.mines;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import static com.vmezhevikin.mines.Constants.*;

public class Frame extends JFrame {

	private static final long serialVersionUID = 8707956500584297464L;
	
	public static final int BUTTON_SIZE = 30;
	public static final int LABEL_WIDTH = 100;
	public static final int LABEL_HEIGHT = 30;
	public static final int PADDING = 1;
	
	public static final Border BORDER = new LineBorder(Color.DARK_GRAY, 1);
	
	public static final Font LABEL_FONT = new Font("Dialog", Font.BOLD, 12);
	public static final Font BUTTON_FONT = new Font("Dialog", Font.BOLD, 10);

	private Field field;
	private RestartListener restartListener;
	private MinePickedListener minePickedListener;
	private IconHolder iconHolder;

	private ScoreLabel scoreLabel;
	private MineButton[][] mineButton;

	public Frame(Field field, Controller controller) {
		super();
		this.field = field;
		this.restartListener = new RestartListener(controller);
		this.minePickedListener = new MinePickedListener(controller);
		setTitle("Mines_v1.1");
		setResizable(false);
		setBounds(150, 100, 900, 510);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addPanel();
		getIcons();
	}

	private void addPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		addScoreLabel(panel);
		addRestartButton(panel);
		addMineButtons(panel);
		setContentPane(panel);
	}

	private void addScoreLabel(JPanel panel) {
		scoreLabel = new ScoreLabel(800, 0);
		scoreLabel.setText(TOTAL_MINES);
		panel.add(scoreLabel);
	}

	private void addRestartButton(JPanel panel) {
		RestartButton restartButton = new RestartButton(0, 0);
		restartButton.setText("Restart");
		restartButton.addActionListener(restartListener);
		panel.add(restartButton);
	}

	private void addMineButtons(JPanel panel) {
		mineButton = new MineButton[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				mineButton[row][col] = new MineButton(row, col);
				panel.add(mineButton[row][col]);
			}
		}
	}

	private void getIcons() {
		iconHolder = new IconHolder();
		iconHolder.readIcons();
	}

	public void prepareView() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				adjustButtonAtPrepare(row, col);
			}
		}
		scoreLabel.setText(field.getMarkedMinesCount());
	}

	private void adjustButtonAtPrepare(int row, int col) {
		mineButton[row][col].setIcon(iconHolder.getIconClosed());
		mineButton[row][col].addMouseListener(minePickedListener);
	}

	public void refreshView() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				adjustButtonAtRefresh(row, col);
			}
		}
		scoreLabel.setText(field.getMarkedMinesCount());
	}

	private void adjustButtonAtRefresh(int row, int col) {
		if (field.cellIsClosed(row, col)) {
			mineButton[row][col].setIcon(iconHolder.getIconClosed());
		}
		if (field.cellIsMarkedAsNotSure(row, col)) {
			mineButton[row][col].setIcon(iconHolder.getIconQuestion());
		}
		if (field.cellIsMarkedAsMine(row, col)) {
			mineButton[row][col].setIcon(iconHolder.getIconMarked());
		}
		if (field.cellIsOpenned(row, col)) {
			int mineCount = field.getMinesNearCell(row, col);
			mineButton[row][col].setIcon(iconHolder.getIconWithNumber(mineCount));
			mineButton[row][col].removeMouseListener(minePickedListener);
		}
	}
	
	public void showViewAtGameEndWithMessageGameOver() {
		showViewAtGameEnd("GAME OVER");
	}
	
	public void showViewAtGameEndWithMessageYouWon() {
		showViewAtGameEnd("YOU WON");
	}

	private void showViewAtGameEnd(String message) {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				adjustButtonAtGameEnd(row, col);
			}
		}
		scoreLabel.setText(message);
	}

	private void adjustButtonAtGameEnd(int row, int col) {
		if (field.cellIsClosed(row, col)) {
			if (field.cellHasMine(row, col)) {
				mineButton[row][col].setIcon(iconHolder.getIconBomb());
			} else {
				mineButton[row][col].setIcon(iconHolder.getIconClosed());
			}
		}
		if (field.cellIsMarkedAsNotSure(row, col)) {
			mineButton[row][col].setIcon(iconHolder.getIconQuestion());
		}
		if (field.cellIsMarkedAsMine(row, col)) {
			if (field.cellHasMine(row, col)) {
				mineButton[row][col].setIcon(iconHolder.getIconMarked());
			} else {
				mineButton[row][col].setIcon(iconHolder.getIconMarkedWrong());
			}
		}
		if (field.cellIsOpenned(row, col)) {
			if (field.cellHasMine(row, col) && row == field.getLastOpennedCellRow() && col == field.getLastOpennedCellCol()) {
				mineButton[row][col].setIcon(iconHolder.getIconBombExploded());
			} else {
				int mineCount = field.getMinesNearCell(row, col);
				mineButton[row][col].setIcon(iconHolder.getIconWithNumber(mineCount));
			}
			
		}
		mineButton[row][col].removeMouseListener(minePickedListener);
	}
}
