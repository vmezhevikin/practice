package com.vemezhevikin.chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Realization of UI
 */
@SuppressWarnings("serial")
public class ChessFrame extends JFrame
{
	private JPanel panel = new JPanel();
	private JButton cell[][] = new JButton[8][8];
	private JLabel turnLabel = new JLabel("TURN");
	private JLabel infoLabel = new JLabel("INFO");
	private ImageIcon[][][][] icons = new ImageIcon[7][3][2][2];

	private ChessModel model = new ChessModel();

	public ChessFrame()
	{
		addFieldButtons();
		addNewGameButton();
		addTurnLabel();
		addInfoLabel();
		readIcons();

		panel.setLayout(null);
		setContentPane(panel);

		refresh();
	}

	private void addFieldButtons()
	{
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
			{
				cell[x][y] = new JButton();
				cell[x][y].setBounds(25 + x * 50, 375 - y * 50, 50, 50);
				cell[x][y].setBorder(null);
				cell[x][y].setActionCommand(x + "" + y);
				cell[x][y].addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent event)
					{
						if (!model.isGameOver())
						{
							String message = event.getActionCommand();
							int x = message.charAt(0) - '0';
							int y = message.charAt(1) - '0';
							model.pickSquare(x, y);
							refresh();
						}
					}
				});
				panel.add(cell[x][y]);
			}
	}

	private void addNewGameButton()
	{
		Font font = new Font("Dialog", Font.BOLD, 10);

		JButton restartButton = new JButton("NEW GAME");
		restartButton.setBounds(450, 25, 100, 25);
		restartButton.setFont(font);
		restartButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				model.startPosition();
				refresh();
			}
		});
		panel.add(restartButton);
	}

	private void addTurnLabel()
	{
		Font font = new Font("Dialog", Font.BOLD, 10);
		LineBorder border = new LineBorder(Color.GRAY, 1);

		turnLabel.setBounds(450, 75, 100, 25);
		turnLabel.setBorder(border);
		turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		turnLabel.setFont(font);
		panel.add(turnLabel);
	}

	private void addInfoLabel()
	{
		Font font = new Font("Dialog", Font.BOLD, 10);
		LineBorder border = new LineBorder(Color.GRAY, 1);

		infoLabel.setBounds(450, 125, 100, 25);
		infoLabel.setBorder(border);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(font);
		panel.add(infoLabel);
	}

	private void readIcons()
	{
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 3; j++)
				for (int k = 0; k < 2; k++)
					for (int m = 0; m < 2; m++)
						icons[i][j][k][m] = new ImageIcon("./Icons/Icon_" + i + j + k + m + ".png");
	}

	private void refresh()
	{
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
			{
				int i0 = model.getPieceType(x, y);
				int i1 = model.getPieceSide(x, y);
				int i2 = model.getSquareColor(x, y);
				int i3 = model.squareIsSelected(x, y);

				cell[x][y].setIcon(icons[i0][i1][i2][i3]);
			}

		turnLabel.setText(model.getTurnSide());
		infoLabel.setText(model.getTurnInfo());
	}
}
