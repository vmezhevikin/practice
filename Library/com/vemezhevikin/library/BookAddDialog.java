package com.vemezhevikin.library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BookAddDialog extends JDialog
{
	private Library library;
	
	private JPanel panel = new JPanel();
	
	private JTextField authorTextField;
	private JTextField titleTextField;
	private JTextField genreTextField;
	private JTextField publisherTextField;

	public BookAddDialog(JFrame owner, Library library)
	{
		super(owner, "Add Book", true);
		this.library = library;
		setResizable(false);
		setBounds(300, 300, 320, 210);
		
		authorTextField = createTextField(10, 10, 300, 30, "Author");
		titleTextField = createTextField(10, 50, 300, 30, "Title");
		genreTextField = createTextField(10, 90, 300, 30, "Genre");
		publisherTextField = createTextField(10, 130, 300, 30, "Publisher");
		
		createButton(10, 170, 145, 30, "Add book", new AddListener());
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
			int id = library.getNextIdBook();
			String author = authorTextField.getText().trim();
			String title = titleTextField.getText().trim();
			String genre = genreTextField.getText().trim();
			String publisher = publisherTextField.getText().trim();
			Book book = new Book(id, author, title, genre, publisher);
			
			if (title.trim().equals(""))
				JOptionPane.showMessageDialog(null, "Enter book title", "", JOptionPane.ERROR_MESSAGE);
			else if (library.enrollBook(book))
				JOptionPane.showMessageDialog(null, "Book added", "", JOptionPane.INFORMATION_MESSAGE);
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
