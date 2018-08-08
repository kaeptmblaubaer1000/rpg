package de.computercamp.rpg;

import de.computercamp.rpg.resources.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

public class Main {
    private static JFrame jf;
    private static JTextArea ta;
    private static JComboBox<Locale> selectLanguageComboBox;
    private static MapBuilder mapBuilder = new MapBuilder();

    public static void main(String[] args) {
    	if(JOptionPane.showOptionDialog(null, "Which language do you want?","Select language",
    	                JOptionPane.YES_NO_CANCEL_OPTION,
    	                JOptionPane.QUESTION_MESSAGE, null, 
    	                new String[]{"English", "German"}, "English") == 0) {
    		Messages.changeLanguage(Locale.GERMAN);
    	}
    	else {
    		Messages.changeLanguage(Locale.ENGLISH);
    	}
    	createJFrame();
        
    }
    
    private static void createJFrame() {
        jf = new JFrame("");
        GridLayout layout = new GridLayout(1, 2);
        ta = new JTextArea(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height - 5);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getClassLoader().getResourceAsStream("de/computercamp/rpg/resources/fonts/NotoSansMono-Regular.ttf"));
            font = font.deriveFont(40f);
            ta.setFont(font);
        } catch (FontFormatException | IOException e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            JOptionPane.showMessageDialog(jf, stringWriter.getBuffer(), "Error while reading font", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        ta.addKeyListener(new KeyHandler());
        ta.setBackground(Color.black);
        ta.setForeground(Color.white);
        ta.setEditable(false);
        ta.setAutoscrolls(false);
        ta.setFocusable(true);
        JPanel panel = new JPanel();
        Locale[] languageList = {Locale.GERMAN, Locale.ENGLISH};
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        panel.add(ta);
        jf.getContentPane().add(BorderLayout.CENTER, panel);
        jf.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        jf.setUndecorated(true);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        renderGame();
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

    static class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    mapBuilder.getPlayer().up();
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    mapBuilder.getPlayer().left();
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    mapBuilder.getPlayer().down();
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    mapBuilder.getPlayer().right();
                    break;
                case KeyEvent.VK_ESCAPE:
                    jf.dispose();
                    System.exit(0);
            }
            renderGame();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    private static void renderGame() {
        consoleClearAndWrite(mapBuilder.getMap().render());
        consoleWrite(mapBuilder.getPlayer().renderInventory());
    }


    static class CloseHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jf.dispose();
            System.exit(0);
        }
    }

    static class SelectLanguageHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(selectLanguageComboBox)) {
                Messages.changeLanguage((Locale) selectLanguageComboBox.getSelectedItem());
            }
        }

    }
}
