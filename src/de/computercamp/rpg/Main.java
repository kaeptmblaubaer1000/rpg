package de.computercamp.rpg;

import de.computercamp.rpg.resources.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static JFrame jf;
    private static JTextArea leftTextArea;
    private static JTextArea rightTextArea;
    private static JComboBox<Locale> selectLanguageComboBox;
    private static MapBuilder mapBuilder = new MapBuilder();
    private static final Object renderLock = new Object();

    private static final int TEXTAREA_WIDTH_PERCENT = 50;

    public static void main(String[] args) {
        if (JOptionPane.showOptionDialog(null, "Which language do you want?", "Select language",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"German", "English"}, "English") == 0) {
            Messages.changeLanguage(Locale.GERMAN);
        } else {
            Messages.changeLanguage(Locale.ENGLISH);
        }
        createJFrame();

    }

    private static void createJFrame() {
        KeyListener keyListener = new KeyHandler();
        jf = new JFrame("");
        jf.setBackground(Color.black);
        jf.setCursor(jf.getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
        leftTextArea = new JTextArea();
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getClassLoader().getResourceAsStream("de/computercamp/rpg/resources/fonts/NotoSansMono-Regular.ttf"));
            font = font.deriveFont(40f);
            leftTextArea.setFont(font);
        } catch (FontFormatException | IOException e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            JOptionPane.showMessageDialog(jf, stringWriter.getBuffer(), "Error whilst reading font", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            throw new Error();
        }
        leftTextArea.addKeyListener(keyListener);
        leftTextArea.setBackground(Color.black);
        leftTextArea.setForeground(Color.white);
        leftTextArea.setEditable(false);
        leftTextArea.setAutoscrolls(false);
        leftTextArea.setFocusable(true);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int leftTextAreaWidth = (int) (((float) screenWidth) * ((float) TEXTAREA_WIDTH_PERCENT)) / 100;
        int rightTextAreaWidth = screenWidth - leftTextAreaWidth;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        leftTextArea.setBounds(0, 0, leftTextAreaWidth, screenHeight);
        rightTextArea = new JTextArea();
        rightTextArea.setBackground(Color.black);
        rightTextArea.setForeground(Color.white);
        rightTextArea.setEditable(false);
        rightTextArea.setAutoscrolls(false);
        rightTextArea.setFocusable(true);
        rightTextArea.addKeyListener(keyListener);
        rightTextArea.setFont(font.deriveFont(25f));
        rightTextArea.setBounds(leftTextAreaWidth, 0, rightTextAreaWidth, screenHeight);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(true);
        panel.add(leftTextArea);
        panel.add(rightTextArea);
        panel.setBackground(Color.black);
        jf.getContentPane().add(BorderLayout.CENTER, panel);
        jf.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, screenHeight);
        jf.setUndecorated(true);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        Timer renderTimer = new Timer(true);
        renderTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (renderLock) {
                    renderGame();
                }
            }
        }, 0, 500);
    }

    public static void clearConsole() {
        leftTextArea.setText("");
    }

    public static void consoleWrite(String text) {
        leftTextArea.setText(leftTextArea.getText() + text + "\n");
    }

    public static void consoleClearAndWrite(String text) {
        clearConsole();
        consoleWrite(text);
    }

    static class KeyHandler extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    jf.dispose();
                    System.exit(0);
                    break;
                case KeyEvent.VK_0:
                    mapBuilder.getPlayer().useItem(0);
                    break;
                case KeyEvent.VK_1:
                    mapBuilder.getPlayer().useItem(1);
                    break;
                case KeyEvent.VK_2:
                    mapBuilder.getPlayer().useItem(2);
                    break;
                case KeyEvent.VK_3:
                    mapBuilder.getPlayer().useItem(3);
                    break;
                case KeyEvent.VK_4:
                    mapBuilder.getPlayer().useItem(4);
                    break;
                case KeyEvent.VK_5:
                    mapBuilder.getPlayer().useItem(5);
                    break;
                case KeyEvent.VK_6:
                    mapBuilder.getPlayer().useItem(6);
                    break;
                case KeyEvent.VK_7:
                    mapBuilder.getPlayer().useItem(7);
                    break;
                case KeyEvent.VK_8:
                    mapBuilder.getPlayer().useItem(8);
                    break;
                case KeyEvent.VK_9:
                    mapBuilder.getPlayer().useItem(9);
                    break;
            }
            synchronized (renderLock) {
                renderGame();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
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
            }
        }
    }

    private static void renderGame() {
        consoleClearAndWrite(mapBuilder.getMap().render());
        consoleWrite(mapBuilder.getPlayer().renderMessagesForPlayer());
        rightTextArea.setText(mapBuilder.getPlayer().renderHealth() + "\n" + mapBuilder.getPlayer().renderInventory());
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
