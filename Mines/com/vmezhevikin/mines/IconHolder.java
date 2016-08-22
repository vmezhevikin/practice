package com.vmezhevikin.mines;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconHolder {

	private static final int KEY_0 = 0;
	private static final int KEY_1 = 1;
	private static final int KEY_2 = 2;
	private static final int KEY_3 = 3;
	private static final int KEY_4 = 4;
	private static final int KEY_5 = 5;
	private static final int KEY_6 = 6;
	private static final int KEY_7 = 7;
	private static final int KEY_8 = 8;
	private static final int KEY_9 = 9;
	private static final int KEY_BOMB = 10;
	private static final int KEY_BOMB_EXPL = 11;
	private static final int KEY_CLOSED = 12;
	private static final int KEY_MARK = 13;
	private static final int KEY_MARK_WRONG = 14;
	private static final int KEY_QUEST = 15;

	private Map<Integer, ImageIcon> icons = new HashMap<>();

	public void readIcons() {
		icons.put(KEY_0, new ImageIcon("./icons/0.png"));
		icons.put(KEY_1, new ImageIcon("./icons/1.png"));
		icons.put(KEY_2, new ImageIcon("./icons/2.png"));
		icons.put(KEY_3, new ImageIcon("./icons/3.png"));
		icons.put(KEY_4, new ImageIcon("./icons/4.png"));
		icons.put(KEY_5, new ImageIcon("./icons/5.png"));
		icons.put(KEY_6, new ImageIcon("./icons/6.png"));
		icons.put(KEY_7, new ImageIcon("./icons/7.png"));
		icons.put(KEY_8, new ImageIcon("./icons/8.png"));
		icons.put(KEY_9, new ImageIcon("./icons/9.png"));
		icons.put(KEY_BOMB, new ImageIcon("./icons/bomb.png"));
		icons.put(KEY_BOMB_EXPL, new ImageIcon("./icons/bomb_exploded.png"));
		icons.put(KEY_CLOSED, new ImageIcon("./icons/empty.png"));
		icons.put(KEY_MARK, new ImageIcon("./icons/mark.png"));
		icons.put(KEY_MARK_WRONG, new ImageIcon("./icons/mark_wrong.png"));
		icons.put(KEY_QUEST, new ImageIcon("./icons/question.png"));
	}

	private Icon getIcon(Integer code) {
		return icons.get(code);
	}

	public Icon getIconClosed() {
		return getIcon(KEY_CLOSED);
	}

	public Icon getIconWithNumber(int mineCount) {
		switch (mineCount) {
		case 0:
			return getIcon(KEY_0);
		case 1:
			return getIcon(KEY_1);
		case 2:
			return getIcon(KEY_2);
		case 3:
			return getIcon(KEY_3);
		case 4:
			return getIcon(KEY_4);
		case 5:
			return getIcon(KEY_5);
		case 6:
			return getIcon(KEY_6);
		case 7:
			return getIcon(KEY_7);
		case 8:
			return getIcon(KEY_8);
		case 9:
			return getIcon(KEY_9);
		default:
			return null;
		}
	}
	
	public Icon getIconBomb() {
		return getIcon(KEY_BOMB);
	}
	
	public Icon getIconBombExploded() {
		return getIcon(KEY_BOMB_EXPL);
	}
	
	public Icon getIconMarked() {
		return getIcon(KEY_MARK);
	}
	
	public Icon getIconMarkedWrong() {
		return getIcon(KEY_MARK_WRONG);
	}
	
	public Icon getIconQuestion() {
		return getIcon(KEY_QUEST);
	}
}
