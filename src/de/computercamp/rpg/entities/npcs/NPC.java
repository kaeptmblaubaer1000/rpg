package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;

import java.text.MessageFormat;

public class NPC extends BaseObject {
    public enum NPCType {TALKING, GIVING_ITEM, HEALTH_CHANGING}

    private NPCType type;
    /*private Item toGive;*/
    private boolean healingOrKilling;
    private int minHealthChange;
    private int maxHealthChange;
    protected long nextUse;
    protected Item requiredItem = null;
    protected int npcMessageID;
    protected long delay;

    public NPC(Vector2D position, int message, long delay) {
        super(position);
        npcMessageID = message;
        type = NPCType.TALKING;
        this.delay = delay;
    }

    /*public NPC(Vector2D position, int message, Item toGive, int delay) {
        super(position);
        npcMessageID = message;
        type = NPCType.GIVING_ITEM;
        this.toGive = toGive;
        nextUse = System.currentTimeMillis() + delay;
    }

    public NPC(Vector2D position, int message, int minHealthChange, int maxHealthChange, int delay) {
        super(position);
        npcMessageID = message;
        type = NPCType.HEALTH_CHANGING;
        this.minHealthChange = minHealthChange;
        this.maxHealthChange = maxHealthChange;
        nextUse = System.currentTimeMillis() + delay;
    }*/

    public void setRequiredItem(Item item) {
        requiredItem = item;
    }

    @Override
    public boolean onPlayerMove(Player player) {
        Vector2D ppos = player.getPosition();
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
            return false;
        }
        if ((Math.abs(ppos.x - position.x) == 1 && ppos.y == position.y) ||
                (Math.abs(ppos.y - position.y) == 1 && ppos.x == position.x)) {
            if (requiredItem != null && !player.getInventory().contains(requiredItem)) {
                MessageFormat messageFormat = new MessageFormat(Messages.itemRequired, Messages.locale);
                player.sendMessage(messageFormat.format(new Object[]{requiredItem.getDisplayName()}));
                return true;
            }
            if (requiredItem != null) {
                player.getInventory().remove(requiredItem);
            }
            requiredItem = null;
            if (System.currentTimeMillis() < nextUse) {
            	MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
                player.sendMessage(messageFormat.format(new Object[]{Math.round((nextUse - System.currentTimeMillis()) / 1000)}));
                return true;
            }
            player.sendMessage(message);
            doNPCAction(player);
            nextUse = System.currentTimeMillis() + delay;
        }
        return true;
    }
    protected void doNPCAction(Player player) {}

    @Override
    public char render() {
        return '\u263a';
    }
}
