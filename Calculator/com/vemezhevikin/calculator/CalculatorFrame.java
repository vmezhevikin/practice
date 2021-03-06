package com.vemezhevikin.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CalculatorFrame extends JFrame
{
	private Expression expression = new Expression();
	
	private JPanel panel = new JPanel();
	private JTextField mainDisplay;
	private JLabel infoDisplay;
	
	private ActionListener symbol = new SymbolListener();
	private ActionListener action = new CommandListener();
	private ActionListener clear = new ClearListener();
	private ActionListener backspace = new BackspaceListener();

	public CalculatorFrame()
	{
		mainDisplay = addTextFiels(15, 15, 300, 50, action);
		infoDisplay = addLabel(15, 65, 300, 25);

		addButton(15, 90, 50, 50, "7", symbol);
		addButton(65, 90, 50, 50, "8", symbol);
		addButton(115, 90, 50, 50, "9", symbol);
		addButton(165, 90, 50, 50, "/", symbol);
		addButton(215, 90, 50, 50, "(", symbol);
		addButton(265, 90, 50, 50, ")", symbol);

		addButton(15, 140, 50, 50, "4", symbol);
		addButton(65, 140, 50, 50, "5", symbol);
		addButton(115, 140, 50, 50, "6", symbol);
		addButton(165, 140, 50, 50, "*", symbol);
		addButton(215, 140, 50, 50, "C", clear);
		addButton(265, 140, 50, 50, "<", backspace);

		addButton(15, 190, 50, 50, "1", symbol);
		addButton(65, 190, 50, 50, "2", symbol);
		addButton(115, 190, 50, 50, "3", symbol);
		addButton(165, 190, 50, 50, "-", symbol);
		addButton(215, 190, 100, 100, "=", action);

		addButton(15, 240, 50, 50, "E", symbol);
		addButton(65, 240, 50, 50, "0", symbol);
		addButton(115, 240, 50, 50, ".", symbol);
		addButton(165, 240, 50, 50, "+", symbol);

		panel.setLayout(null);
		setContentPane(panel);
	}

	private JButton addButton(int posX, int posY, int width, int height, String text, ActionListener listener)
	{
		Font fontText = new Font("Dialog", Font.BOLD, 14);
		JButton newButton = new JButton();

		newButton.setBounds(posX + 1, posY + 1, width - 2, height - 2);
		newButton.setText(text);
		newButton.setFont(fontText);
		newButton.setActionCommand(text);
		newButton.addActionListener(listener);

		panel.add(newButton);
		return newButton;
	}

	private JTextField addTextFiels(int posX, int posY, int width, int height, ActionListener listener)
	{
		Font fontText = new Font("Dialog", Font.PLAIN, 20);
		JTextField newTextField = new JTextField();

		newTextField.setBounds(posX, posY, width, height);
		newTextField.setText("");
		newTextField.setFont(fontText);
		newTextField.setHorizontalAlignment(JTextField.RIGHT);
		newTextField.addActionListener(listener);

		panel.add(newTextField);
		return newTextField;
	}

	private JLabel addLabel(int posX, int posY, int width, int height)
	{
		Font fntText = new Font("Dialog", Font.BOLD, 10);
		JLabel newLabel = new JLabel();

		newLabel.setBounds(posX, posY, width, height);
		newLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		newLabel.setFont(fntText);
		panel.add(newLabel);

		return newLabel;
	}

	private class SymbolListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String text = mainDisplay.getText();
			String symbol = event.getActionCommand();

			mainDisplay.setText(text + symbol);
		}
	}

	private class ClearListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			mainDisplay.setText("");
			infoDisplay.setText("");
		}
	}

	private class BackspaceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String text = mainDisplay.getText();
			int length = text.length();

			if (length > 0)
				mainDisplay.setText(text.substring(0, length - 1));
		}
	}

	private class CommandListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String text = mainDisplay.getText();
			int length = text.length();
			
			if (length != 0)
			{
				expression.set(text);
				if (expression.isValid())
				{
					infoDisplay.setText(mainDisplay.getText() + "= ");
					mainDisplay.setText(expression.toString());
				} else
				{
					infoDisplay.setText(expression.getValidationString());
				}
			}
		}
	}
}
