package de.computercamp.rpg;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {
    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JTextArea jta = new JTextArea(40, 100);
//        frame.add(new JScrollPane(jta));
//        jta.setLineWrap(true);
//        jta.setWrapStyleWord(true);
//        jta.setText("☃");
//        jta.setFont(new Font("Consolas", Font.PLAIN, 50));
//        frame.pack();
//        frame.setVisible(true);
        JFrame jf = new JFrame("Demo");
        JTextArea ta1 = new JTextArea(500, 500);
        Character c = '\u0645';
        ta1.setText("" + c);
        ta1.setFont(new Font("Consolas", Font.PLAIN, 50));
        jf.add(new JScrollPane(ta1));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1000, 1000);
        jf.setVisible(true);
    }
}
