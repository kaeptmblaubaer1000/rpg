package de.computercamp.rpg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Main {
	private static JButton closeButton;
	private static JFrame jf;
	private static JTextArea ta;

	public static void main(String[] args) {
		// JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JTextArea ta = new JTextArea(40, 100);
		// frame.add(new JScrollPane(jta));
		// jta.setLineWrap(true);
		// jta.setWrapStyleWord(true);
		// jta.setText("â˜ƒ");
		// jta.setFont(new Font("Consolas", Font.PLAIN, 50));
		// frame.pack();
		// frame.setVisible(true);
		jf = new JFrame("Demo");
		jf.addKeyListener(new KeyHandler());
        jf.setFocusTraversalKeysEnabled(false);
        jf.setFocusable(true);
		ta = new JTextArea(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height - 5);
		ta.setFont(new Font("Consolas", Font.PLAIN, 50));
		ta.setBackground(Color.black);
		ta.setForeground(Color.white);
		ta.setEditable(false); 
		closeButton = new JButton("Schließen");
		closeButton.setBackground(Color.red);
		closeButton.setForeground(Color.white);
		closeButton.addActionListener(new CloseHandler());
		JScrollPane scroll = new JScrollPane(ta,
	            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		JPanel closepanel = new JPanel();
		closepanel.setLayout(new FlowLayout());
		panel.add(scroll);
		closepanel.add(closeButton);
		panel.add(closepanel);
		jf.getContentPane().add(BorderLayout.CENTER, panel);
		jf.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
//		jf.setUndecorated(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void ClearConsole() {
		ta.setText("");
	}
	public static void ConsoleWrite(String text) {
		ta.setText(ta.getText()+"\r\n"+text);
	}
	public static void ConsoleClearAndWrite(String text) {
		ClearConsole();
		ConsoleWrite(text);
	}
	static class KeyHandler implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("KEY PRESSED: "+e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("KEY RELEASED: "+e.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("KEY TYPED: "+e.getKeyCode());
		}
	}

	static class CloseHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			jf.dispose();
		}
	}
}
