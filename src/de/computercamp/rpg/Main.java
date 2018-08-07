package de.computercamp.rpg;

import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Main {
	private static JButton closeButton;
	private static JFrame jf;
	private static JTextArea ta;
	private static JComboBox<Locale> selectLanguageComboBox;
	public static Player player = new Player(new Vector2D(0, 0));
	public static Map map = MapBuilder.getMap1(player);
	public static void main(String[] args) {
		jf = new JFrame("");
		jf.addKeyListener(new KeyHandler());
		ta = new JTextArea(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height - 5);
		ta.setFont(new Font("Consolas", Font.PLAIN, 50));
		ta.setBackground(Color.black);
		ta.setForeground(Color.white);
		ta.setEditable(false);
		closeButton = new JButton(Messages.closeProgram);
		closeButton.setBackground(Color.red);
		closeButton.setForeground(Color.white);
		closeButton.addActionListener(new CloseHandler());
		closeButton.setText(Messages.closeProgram);
		JScrollPane scroll = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panel = new JPanel();
		Locale[] languageList = { Locale.GERMAN, Locale.ENGLISH };
		JComboBox<Locale> selectLanguageComboBox = new JComboBox<Locale>(languageList);
		if(Locale.getDefault().equals(Locale.GERMAN))
		selectLanguageComboBox.setSelectedIndex(0);
		else
			selectLanguageComboBox.setSelectedIndex(1);
		selectLanguageComboBox.addActionListener(new SelectLanguageHandler());
		selectLanguageComboBox.setBackground(Color.green);
		selectLanguageComboBox.setForeground(Color.black);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		JPanel closepanel = new JPanel();
		closepanel.setLayout(new FlowLayout());
		panel.add(scroll);
		closepanel.add(closeButton);
		closepanel.add(selectLanguageComboBox);
		panel.add(closepanel);
		jf.getContentPane().add(BorderLayout.CENTER, panel);
		jf.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		jf.setUndecorated(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		player.setMap(map);
		consoleClearAndWrite(map.render());
		closepanel.setBackground(Color.black);
//		new Background(closepanel, 1);
	}

	public static void clearConsole() {
		ta.setText("");
	}

	public static void consoleWrite(String text) {
		ta.setText(ta.getText() + text + "\n");
	}

	public static void consoleClearAndWrite(String text) {
		clearConsole();
		consoleWrite(text);
	}

	static class KeyHandler implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				player.up();
				consoleClearAndWrite(map.render());
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				player.left();
				consoleClearAndWrite(map.render());
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				player.down();
				consoleClearAndWrite(map.render());
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				player.right();
				consoleClearAndWrite(map.render());
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}

	static class CloseHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	static class SelectLanguageHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(selectLanguageComboBox)) {
				Messages.changeLanguage((Locale)selectLanguageComboBox.getSelectedItem());
			}
		}

	}
}
