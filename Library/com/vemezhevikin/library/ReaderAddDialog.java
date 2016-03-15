package com.vemezhevikin.library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ReaderAddDialog extends JDialog
{
	private Library library;
	
	private JPanel panel = new JPanel();
	private JTextField surnameTextField;
	private JTextField nameTextField;
	private JTextField patronymicTextField;
	private JTextField birthDateTextField;

	public ReaderAddDialog(JFrame owner, Library library)
	{
		super(owner, "Add Reader", true);
		this.library = library;
		setResizable(false);
		setBounds(300, 300, 320, 210);
		
		surnameTextField = createTextField(10, 10, 300, 30, "Surname");
		nameTextField = createTextField(10, 50, 300, 30, "Name");
		patronymicTextField = createTextField(10, 90, 300, 30, "Patronymic");
		birthDateTextField = createTextField(10, 130, 300, 30, "Date of birth");
		
		createButton(10, 170, 145, 30, "Add reader", new AddListener());
		createButton(165, 170, 145, 30, "Close", new CancelListener());

		panel.setLayout(null);
		add(panel);
	}

	private JButton createButton(int posX, int posY, int width, int height, String text, ActionListener listener)
	{
		Font fntText = new Font("Dialog", Font.BOLD, 10);
		JButton newButton = new JButton();

		newButton.setBounds(posX, posY, width, height);
		newButton.setText(text);
		newButton.setFont(fntText);
		newButton.setEnabled(true);
		newButton.addActionListener(listener);
		panel.add(newButton);

		return newButton;
	}

	private JTextField createTextField(int posX, int posY, int width, int height, String text)
	{
		Font fntText = new Font("Dialog", Font.BOLD, 10);
		JTextField newTextField = new JTextField();

		newTextField.setText(text);
		newTextField.setBounds(posX, posY, width, height);
		newTextField.setHorizontalAlignment(SwingConstants.LEFT);
		newTextField.setFont(fntText);
		panel.add(newTextField);

		return newTextField;
	}
	
	private class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int id = library.getNextIdReader();
			String surname = surnameTextField.getText().trim();
			String name = nameTextField.getText().trim();
			String patronymic = patronymicTextField.getText().trim();
			String birthDate = birthDateTextField.getText().trim();
			Reader reader = new Reader(id, name, surname, patronymic, birthDate);
			
			if (surname.trim().equals(""))
				JOptionPane.showMessageDialog(null, "Enter reader surname", "", JOptionPane.ERROR_MESSAGE);
			else if (library.enrollReader(reader))
				JOptionPane.showMessageDialog(null, "Reader added", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class CancelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			setVisible(false);
		}
	}

}
