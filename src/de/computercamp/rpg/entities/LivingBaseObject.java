package de.computercamp.rpg.entities;

import java.util.Timer;
import java.util.TimerTask;

import de.computercamp.rpg.Vector2D;

public class LivingBaseObject extends BaseObject {
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
        this.health = this.health + health;
    }

    public void decreaseHealth(int health) {
        this.health = this.health - health;
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
	@Override
	public char render() {
		return 0;
	}

}
