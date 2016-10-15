package com.vmezhevikin.ticktacktoe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Constants {

	public static final int FIELD_ROWS = 15;
	public static final int FIELD_COLS = 15;

	public static final int BUTTON_SIZE = 30;
	public static final int LABEL_WIDTH = 450;
	public static final int LABEL_HEIGHT = 30;
	public static final int PADDING = 1;
	
	public static final Border BUTTON_BORDER = new LineBorder(Color.DARK_GRAY, 1);
	public static final Font BUTTON_FONT = new Font("Dialog", Font.BOLD, 10);
	
	public static final Color CELL_EMPTY_COLOR = new Color(255, 255, 255);
	public static final Color CELL_USER_COLOR = new Color(200, 0, 0);
	public static final Color CELL_COMP_COLOR = new Color(0, 200, 0);

	public static final int CELL_EMPTY = 0;
	public static final int CELL_COMP_MARK = 1;
	public static final int CELL_USER_MARK = 2;
	
	public static final int USER_TURN = 0;
	public static final int COMP_TURN = 1;
	
	public static final int GAME_CONTINUE = 0;
	public static final int GAME_OVER = 1;
	public static final int GAME_DRAW = 2;
	
	public static final int CELL_NOTFOUND = 0;
	public static final int CELL_FOUND = 1;
}