package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;

public class Monster extends NPC {

	public Monster(Player player, Vector2D position, MessageID message, long delay) {
		super(player, position, message, delay);
		// TODO Auto-generated constructor stub
	}
    public void startFighting(Player player) {
    	Thread rumlaufTimer = new Thread(new Runnable() {
		    @Override
	       	public void run() {
		    	while(true) {
		    		if (health > 0) {
			    		if (Math.abs(player.getPosition().x-position.x) > 1) {
			    			if (player.getPosition().x < position.x) {
			    				setPosition(new Vector2D(position.x-1, position.y));
			    			} else {
			    				setPosition(new Vector2D(position.x+1, position.y));
			    			}
			    		} else if (Math.abs(player.getPosition().y-position.y) > 1) {
			    			if (player.getPosition().y < position.y) {
			    				setPosition(new Vector2D(position.x, position.y-1));
			    			} else {
			    				setPosition(new Vector2D(position.x, position.y+1));
			    			}
			    		} else {
			    			player.decreaseHealth(5);
			    		}
			    		
			    		try {
							Thread.sleep(500);
						} catch (InterruptedException e) {}
		    		}
		    	}
				
			}
	    });
        
    	rumlaufTimer.start();
    }
	@Override
	public char render() {
        if (!isDead()) {
    		return '\u2e0e';
    	} else {
    		if (despawn == 0) {
    			despawn = System.currentTimeMillis()+5000;
    		}
    		if (System.currentTimeMillis() >= despawn && map.getMapContents().contains(this))
    			map.removeObject(this);
    		
    		return 'X';
    	}
	}
}
