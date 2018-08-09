package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;

import java.text.MessageFormat;


public class NPC extends LivingBaseObject {
    /*private Item toGive;*/
    private int minHealthChange;
    private int maxHealthChange;
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
    	if (health > 0) {
    		return '\uA66A';
    	} else {
    		return 'X';
    	}
    }
}
