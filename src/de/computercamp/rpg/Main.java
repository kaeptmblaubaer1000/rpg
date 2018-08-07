package de.computercamp.rpg;

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
import java.util.ResourceBundle;

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
	private static Locale language = Locale.getDefault();

	public static void main(String[] args) {
		jf = new JFrame("");
		ta = new JTextArea(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height - 5);
		ta.setFont(new Font("Consolas", Font.PLAIN, 50));
		ta.addKeyListener(new KeyHandler());
		ta.setBackground(Color.black);
		ta.setForeground(Color.white);
		ta.setEditable(false);
		closeButton = new JButton(Messages.closeProgram);
		closeButton.setBackground(Color.red);
		closeButton.setForeground(Color.white);
		closeButton.addActionListener(new CloseHandler());
		JScrollPane scroll = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panel = new JPanel();
		String[] languageList = { "Deutsch", "Englisch" };
		JComboBox<String> selectLanguageComboBox = new JComboBox<String>(languageList);
		selectLanguageComboBox.setSelectedIndex(0);
		selectLanguageComboBox.addActionListener(new SelectLanguageHandler());
		selectLanguageComboBox.setBackground(Color.green);
		selectLanguageComboBox.setForeground(Color.black);
		UIManager.put("ComboBox.selectionBackground", Color.yellow);
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
	}

	public static void ClearConsole() {
		ta.setText(" ");
	}

	public static void ConsoleWrite(String text) {
		ta.setText(ta.getText() + text + "\r\n");
	}

	public static void ConsoleClearAndWrite(String text) {
		ClearConsole();
		ConsoleWrite(text);
	}
	
	public static String GetLanguageText(String key) {
		return ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", language)
		.getString(key);
	}

	public static void ConsoleWriteInLanguage(String key) {
		ta.setText(ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", language).getString(key));
	}

	public static void ConsoleWriteInLanguageAndClear(String key) {
		ClearConsole();
		ConsoleWriteInLanguage(key);
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
				ConsoleClearAndWrite("Lauf nach vorne!");
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				ConsoleClearAndWrite("Lauf nach links!");
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				ConsoleClearAndWrite("Lauf nach hinten!");
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				ConsoleClearAndWrite("Lauf nach rechts!");
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
			jf.dispose();
		}
	}

	static class SelectLanguageHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
			switch ((String) comboBox.getSelectedItem()) {
			case "Deutsch":
				language = new Locale("de", "DE");
				break;
			default:
				language = Locale.ENGLISH;
			}
			closeButton.setText(ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", language)
					.getString("closeButton"));
		}

	}
}
