package com.vemezhevikin.tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class TetrisFrame extends JFrame
{
	private JPanel panel = new JPanel();
	private JLabel[][] gameField = new JLabel[20][10];
	private JLabel[][] nextField = new JLabel[4][4];
	private JLabel levelTable;
	private JLabel scoreTable;
	private ImageIcon[] icon = new ImageIcon[3];
	private Timer timer;
	private LogicBlock logic = new LogicBlock();

	public TetrisFrame()
	{
		for (int row = 0; row < 20; row++)
			for (int col = 0; col < 10; col++)
				gameField[row][col] = createLabel(25 + col * 25, 500 - row * 25, 25, 25);

		createLabelForText(300, 25, 100, 25, "LEVEL");
		levelTable = createLabelForText(300, 75, 100, 25, "X");
		createLabelForText(300, 125, 100, 25, "SCORE");
		scoreTable = createLabelForText(300, 175, 100, 25, "X");

		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
				nextField[row][col] = createLabel(300 + col * 25, 325 - row * 25, 25, 25);
		
		createButton(300, 450, 100, 25, "PAUSE", new PauseListener());
		createButton(300, 500, 100, 25, "RESTART", new RestartListener());

		createKeyboardListener(new KeyboardListener());
		
		timer = createTimer(new TimerListener());
		
		panel.setLayout(null);
		setContentPane(panel);

		getIcons();
		refresh();
	}

	private void getIcons()
	{
		icon[0] = new ImageIcon("./Icons/Icon_Empty.png");
		icon[1] = new ImageIcon("./Icons/Icon_Accupied.png");
		icon[2] = new ImageIcon("./Icons/Icon_Figure.png");
	}

	private void refresh()
	{
		for (int row = 0; row < 20; row++)
			for (int col = 0; col < 10; col++)
				gameField[row][col].setIcon(icon[logic.fieldStateAt(row, col)]);
		
		levelTable.setText(logic.getLevel());
		scoreTable.setText(logic.getScore());
		
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
				nextField[row][col].setIcon(icon[logic.nextFigureStateAt(row, col)]);
		
		timer.setDelay(logic.getTimerTime());
	}

	private JButton createButton(int posX, int posY, int width, int height, String text, ActionListener listener)
	{
		Font fntText = new Font("Dialog", Font.BOLD, 10);
		JButton newButton = new JButton();

		newButton.setBounds(posX, posY, width, height);
		newButton.setText(text);
		newButton.setFont(fntText);
		newButton.addActionListener(listener);
		newButton.setFocusable(false);
		panel.add(newButton);

		return newButton;
	}
	
	private JLabel createLabelForText(int posX, int posY, int width, int height, String text)
	{
		Font fntText = new Font("Dialog", Font.BOLD, 10);
		LineBorder border = new LineBorder(Color.GRAY, 1);
		JLabel newLabel = new JLabel();

		newLabel.setText(text);
		newLabel.setBounds(posX, posY, width, height);
		newLabel.setBorder(border);
		newLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newLabel.setFont(fntText);
		panel.add(newLabel);

		return newLabel;
	}

	private JLabel createLabel(int posX, int posY, int width, int height)
	{
		JLabel newLabel = new JLabel();

		newLabel.setBounds(posX, posY, width, height);
		newLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(newLabel);

		return newLabel;
	}
	
	private void createKeyboardListener(KeyAdapter adapter)
	{
		panel.setFocusable(true);
		panel.addKeyListener(adapter);
	}
	
	private Timer createTimer(ActionListener listener)
	{
		Timer newTimer = new Timer(1000, listener);
		newTimer.start();
		
		return newTimer;
	}
	
	private class PauseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if (!timer.isRunning() && !logic.gameOver())
				timer.start();
			else
				timer.stop();
		}
	}
	
	private class RestartListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			logic.firstTurn();
			timer.start();
			refresh();
		}
	}
	
	private class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			logic.moveFigureDown();
			if (logic.gameOver())
				timer.stop();
			refresh();
		}	
	}
	
	private class KeyboardListener extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent event)
		{
			if (timer.isRunning())
			{
				switch(event.getKeyCode())
				{
				case KeyEvent.VK_DOWN:
					logic.dropFigure();
					break;
				case KeyEvent.VK_LEFT:
					logic.moveFigureLeft();
					break;
				case KeyEvent.VK_RIGHT:
					logic.moveFigureRight();
					break;
				case KeyEvent.VK_UP:
					logic.rotateFigure();
					break;
				default:
					break;
				}
				refresh();
			}
		}
	}
}
