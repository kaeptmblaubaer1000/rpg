package de.computercamp.rpg;

import de.computercamp.rpg.resources.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static final Object renderLock = new Object();
    private static final int TEXTAREA_WIDTH_PERCENT = 50;
    private static JFrame jf;
    private static JTextArea leftTextArea;
    private static JTextArea rightTextArea;
    private static Game game = Game.Companion.create(); // The ".Companion" part will disappear in a Kotlin Main.
    private static boolean debugMode = false;

    public static void main(String[] args) {
        int option = JOptionPane.showOptionDialog(null, "Choose your language?", "Select language",
            JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"German", "English"},
            "English");
        if (option == 0) {
            Messages.changeLanguage(Locale.GERMAN);
        } else if (option == -1) {
            System.exit(0);
        } else {
            Messages.changeLanguage(Locale.ENGLISH);
        }
        createJFrame();

    }

    private static void createJFrame() {
        KeyListener keyListener = new KeyHandler();
        jf = new JFrame("");
        jf.setBackground(Color.BLACK);
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
        leftTextArea.setBackground(Color.DARK_GRAY);
        leftTextArea.setForeground(Color.WHITE);
        leftTextArea.setEditable(false);
        leftTextArea.setAutoscrolls(false);
        leftTextArea.setFocusable(true);
        leftTextArea.setDragEnabled(false);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int leftTextAreaWidth = (int) (((float) screenWidth) * ((float) TEXTAREA_WIDTH_PERCENT)) / 100;
        int rightTextAreaWidth = screenWidth - leftTextAreaWidth;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        leftTextArea.setBounds(0, 0, leftTextAreaWidth, screenHeight);
        rightTextArea = new JTextArea();
        rightTextArea.setBackground(Color.DARK_GRAY);
        //rightTextArea.setForeground(new Color(213, 213, 213));
        rightTextArea.setForeground(Color.WHITE);
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
        panel.setBackground(Color.BLACK);
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

    private static void renderGame() {
        consoleClearAndWrite(game.getCurrentRoom().render());
        consoleWrite(game.getPlayer().renderMessage());
        rightTextArea.setText(game.getPlayer().renderHealth() + "\n\n" +
            game.getPlayer().renderInventory() + "\n\n" +
            game.getPlayer().renderCoins()
        );
        if (debugMode) {
            rightTextArea.setText(rightTextArea.getText() + "\n\n" + game.getPlayer().getPosition().toString());
        }
    }

    static class KeyHandler extends KeyAdapter {
        private boolean qPressed = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                qPressed = true;
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                game.getPlayer().up();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                game.getPlayer().left();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                game.getPlayer().down();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                game.getPlayer().right();
                break;
            }
            synchronized (renderLock) {
                renderGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                jf.dispose();
                System.exit(0);
                break;

            case KeyEvent.VK_0:
                numberKeyPressed(0);
                break;
            case KeyEvent.VK_1:
                numberKeyPressed(1);
                break;
            case KeyEvent.VK_2:
                numberKeyPressed(2);
                break;
            case KeyEvent.VK_3:
                numberKeyPressed(3);
                break;
            case KeyEvent.VK_4:
                numberKeyPressed(4);
                break;
            case KeyEvent.VK_5:
                numberKeyPressed(5);
                break;
            case KeyEvent.VK_6:
                numberKeyPressed(6);
                break;
            case KeyEvent.VK_7:
                numberKeyPressed(7);
                break;
            case KeyEvent.VK_8:
                numberKeyPressed(8);
                break;
            case KeyEvent.VK_9:
                numberKeyPressed(9);
                break;
            case KeyEvent.VK_Q:
                qPressed = false;
                break;
            case KeyEvent.VK_F3:
                debugMode = !debugMode;
            }
            synchronized (renderLock) {
                renderGame();
            }
        }

        private void numberKeyPressed(int number) {
            if (qPressed) {
                game.getPlayer().dropItem(number);
            } else {
                game.getPlayer().useItem(number);
            }
        }
    }
}
