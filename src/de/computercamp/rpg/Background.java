package de.computercamp.rpg;

import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Background extends TimerTask {
	private JFrame frame;
	private Boolean minusred = false;
	private Boolean minusgreen = false;
	private Boolean minusblue = false;
	private JPanel panel;
	private int slowness;

	public Background(JFrame frame, int slowness) {
		this.slowness = slowness;
		this.frame = frame;
		Timer timer = new Timer();
		timer.schedule(this, 0, 10);
	}

	public Background(JPanel panel, int slowness) {
		this.slowness = slowness;
		this.panel = panel;
		Timer timer = new Timer();
		timer.schedule(this, 0, 10);
	}

	public void run() {
		int red = 0;
		int green = 0;
		int blue = 0;
		if (panel == null) {
			red = frame.getContentPane().getBackground().getRed();
			green = frame.getContentPane().getBackground().getGreen();
			blue = frame.getContentPane().getBackground().getBlue();
		} else {
			red = panel.getBackground().getRed();
			green = panel.getBackground().getGreen();
			blue = panel.getBackground().getBlue();
		}
		Random random = new Random();
		switch (random.nextInt(3)) {
		case 0:
			if (minusred)
				red -= slowness;
			else
				red += slowness;
			if (red >= 255) {
				red = 255;
				minusred = true;
			} else if (red < 0) {
				red = 0;
				minusred = false;
			}
			break;
		case 1:
			if (minusgreen)
				green -= slowness;
			else
				green += slowness;
			if (green >= 255) {
				green = 255;
				minusgreen = true;
			} else if (green < 0) {
				green = 0;
				minusgreen = false;
			}
			break;
		case 2:
			if (minusblue)
				blue -= slowness;
			else
				blue += slowness;
			if (blue >= 255) {
				blue = 255;
				minusblue = true;
			} else if (blue < 0) {
				blue = 0;
				minusblue = false;
			}
			break;
		}
		if (panel == null)
			frame.getContentPane().setBackground(new Color(red, green, blue));
		else
			panel.setBackground(new Color(red, green, blue));
	}
}