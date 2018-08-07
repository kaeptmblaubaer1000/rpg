package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.resources.Messages;

import java.text.MessageFormat;

public class NPC extends BaseObject {
    public enum NPCType {TALKING, GIVING_ITEM, HEALTH_CHANGING}

    private NPCType type;
    private String message;
    private Item toGive;
    private boolean healingOrKilling;
    private int minHealthChange;
    private int maxHealthChange;
    private long nextUse;
    private Item requiredItem = null;
    private int npcMessageID;

    public NPC(Vector2D position, int message) {
        super(position);
        npcMessageID = message;
        type = NPCType.TALKING;
    }

    public NPC(Vector2D position, int message, Item toGive, int delay) {
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
    }

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
        if ((player.getPosition().x == ppos.x) && (player.getPosition().y == ppos.y)) {
            return false;
        }
        if ((Math.abs(ppos.x - position.x) == 1 && ppos.y == position.y) ||
                (Math.abs(ppos.y - position.y) == 1 && ppos.x == position.x)) {
            if (requiredItem != null && !player.getInventory().contains(requiredItem)) {
                MessageFormat messageFormat = new MessageFormat(Messages.itemRequired, Messages.locale);
                player.sendMessage(messageFormat.format(new Object[]{requiredItem.getDisplayName()}));
            }
            if (requiredItem != null) {
                player.getInventory().remove(requiredItem);
            }
            requiredItem = null;
            if (type == NPCType.TALKING) {
                player.sendMessage(message);
            } else if (type == NPCType.GIVING_ITEM) {
                if (System.currentTimeMillis() >= nextUse) {
					/*toGive.position.x = player.position.x;
					toGive.position.y = player.position.y;
					player.map.addObject(toGive);*/
                    player.collectItem(toGive);
                    player.sendMessage(message);
                } else {
                    MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
                    player.sendMessage(messageFormat.format(new Object[]{Math.round((nextUse - System.currentTimeMillis()) / 1000)}));
                }
            } else if (type == NPCType.HEALTH_CHANGING) {
                if (System.currentTimeMillis() >= nextUse) {
                    int healthChange = (int) Math.round((Math.random() * (maxHealthChange - minHealthChange)) + minHealthChange);
                    player.sendMessage(message.replace("{HEALTHCHANGE}", String.valueOf(healthChange)));

                    if (healthChange < 0) {
                        player.decreaseHealth((-1) * healthChange);
                    } else {
                        player.increaseHealth(healthChange);
                    }
                } else {
                    MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
                    player.sendMessage(messageFormat.format(new Object[]{Math.round((nextUse - System.currentTimeMillis()) / 1000)}));
                }
            }
        }
        return true;
    }

    @Override
    public char render() {
        return '\u2638';
    }
}
