package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;

import java.text.MessageFormat;


public class NPC extends LivingBaseObject {
    protected long nextUse;
    protected Item requiredItem = null;
    protected int npcMessageID;
    protected long delay;

    public NPC(Vector2D position, int message, long delay) {
        super(position);
        npcMessageID = message;
        this.delay = delay;
    }

    public void setRequiredItem(Item item) {
        requiredItem = item;
    }

    @Override
    public boolean onPlayerMove(Player player) {
    	if (health <= 0) {
    		return true;
    	}
        String message;
        if (npcMessageID == 0) {
        	message = Messages.npcWelcome;
        } else if (npcMessageID == 1) {
        	message = Messages.npcMagician;
        } else if (npcMessageID == 2) {
        	message = Messages.npcBadMagician;
        } else if (npcMessageID == 3) {
        	message = Messages.npcWeaponsmith;
        } else {
        	message = "Error: Message not found.";
        }
        if ((player.getPosition().x == position.x) && (player.getPosition().y == position.y)) {
            if (requiredItem != null && !player.getInventory().contains(requiredItem)) {
                MessageFormat messageFormat = new MessageFormat(Messages.itemRequired, Messages.locale);
                player.sendMessage(messageFormat.format(new Object[]{requiredItem.getDisplayName()}));
                return false;
            }
            if (requiredItem != null) {
                player.getInventory().remove(requiredItem);
            }
            requiredItem = null;
            if (System.currentTimeMillis() < nextUse) {
            	MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
                player.sendMessage(messageFormat.format(new Object[]{Math.round((nextUse - System.currentTimeMillis()) / 1000)}));
                return false;
            } else if (nextUse == -1) {
            	player.sendMessage(Messages.npcWaitingForever);
            	return false;
            }
            player.sendMessage(message);
            doNPCAction(player);
            if (delay == -1)
            	nextUse = -1;
            else 
            	nextUse = System.currentTimeMillis() + delay;
            
            return false;
        }
        return true;
    }
    protected void doNPCAction(Player player) {}

    @Override
    public char render() {
        if (!isDead()) {
    		return '\uA66A';
    	} else {
    		return 'X';
    	}
    }
    public void startMoving(Player player) {
    	Thread rumlaufTimer = new Thread(new Runnable() {
		    @Override
	       	public void run() {
		    	short richtung = (short) Math.round(Math.random()*3);
				while (true) {
					Vector2D ziel = new Vector2D(0, 0);
					if (richtung == 0) {
						ziel = new Vector2D(position.x+1, position.y);
					} else if (richtung == 1) {
						ziel = new Vector2D(position.x-1, position.y);
					} else if (richtung == 2) {
						ziel = new Vector2D(position.x, position.y+1);
					} else if (richtung == 3) {
						ziel = new Vector2D(position.x, position.y-1);
					}
					
					if (map != null) {
						if (!((player.getPosition().x == position.x-1 || player.getPosition().x == position.x+1 || player.getPosition().x == position.x) && 
								(player.getPosition().y == position.y-1 || player.getPosition().y == position.y+1 || player.getPosition().y == position.y))) {
							if (map.getObjectByPosition(ziel) == null && !ziel.equals(player.getPosition()) && (ziel.x > 0 && ziel.y > 0 && ziel.x < 59 && ziel.y < 15)) {
								if (health > 0) {
									setPosition(ziel);
								}
							} else {
								richtung = (short) Math.round(Math.random()*3);
							}
						}
					}
					try {
						Thread.sleep(Math.round(Math.random()*5000));
					} catch (InterruptedException e) {}
				}
				
			}
	    });
        
    	rumlaufTimer.start();
    }
}
