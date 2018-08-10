package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

import java.util.Timer;
import java.util.TimerTask;

public abstract class LivingBaseObject extends BaseObject {

    public static final int MAX_HEALTH = 20;
    protected int health = MAX_HEALTH;

    public LivingBaseObject(Vector2D position) {
		super(position);
		Timer healthTimer = new Timer(true);
		
        healthTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
            public void run() {
                if (health < MAX_HEALTH && health > 0) {
                    health++;
                }
            }
        }, 0, 2000);
	}

    public void increaseHealth(int health) {
        int finalHealth = this.health + health;
        if (finalHealth > MAX_HEALTH) {
            this.health = MAX_HEALTH;
        } else {
            this.health = finalHealth;
        }
    }

    public void decreaseHealth(int health) {
        int finalHealth = this.health - health;
        if (finalHealth < 0) {
            this.health = 0;
        } else {
            this.health = finalHealth;
        }
    }

    /**
     * Sets the health to MAX_HEALTH
     */
    public void heal() {
        health = MAX_HEALTH;
    }

    /**
     * Sets the health to 0
     */
    public void kill() {
        health = 0;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public String renderHealth() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < health; i++) {
            //string.append('\u2665');
            string.append('+');
        }
        for (int i = 0; i < MAX_HEALTH - health; i++) {
            //string.append('\u2661');
            string.append('-');
        }
        string.insert(MAX_HEALTH / 2, '\n');
        return string.toString();
    }
}
