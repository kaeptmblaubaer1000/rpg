package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

public class NPC extends BaseObject{
    public enum NPCType {TALKING, GIVING_ITEM, HEALTH_CHANGING}

    private NPCType type;
	private String message;
	private Item toGive;
	private boolean healingOrKilling;
	private int minHealthChange;
	private int maxHealthChange;
	private long nextUse;
	public NPC(Vector2D position, String message) {
		super(position);
		this.message = message;
		type = NPCType.TALKING;
	}
	public NPC(Vector2D position, String message, Item toGive, int delay) {
		super(position);
		this.message = message;
		type = NPCType.GIVING_ITEM;
		this.toGive = toGive;
		nextUse = System.currentTimeMillis() + delay;
	}
	public NPC(Vector2D position, String message, int minHealthChange, int maxHealthChange, int delay) {
		super(position);
		this.message = message;
		type = NPCType.HEALTH_CHANGING;
		this.minHealthChange = minHealthChange;
		this.maxHealthChange = maxHealthChange;
		nextUse = System.currentTimeMillis() + delay;
	}
	@Override
    public boolean onPlayerMove(Player player) {
		Vector2D ppos = player.getPosition();
		if ((player.getPosition().x == ppos.x) && (player.getPosition().y == ppos.y)) {
			return false;
		}
        if ((Math.abs(ppos.x - position.x) == 1 && ppos.y == position.y) ||
                (Math.abs(ppos.y - position.y) == 1 && ppos.x == position.x)) {
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
					player.sendMessage("You have to wait another " + Math.round((nextUse-System.currentTimeMillis())/1000) + " seconds to get an item again!");
				}
			} else if (type == NPCType.HEALTH_CHANGING) {
				if (System.currentTimeMillis() >= nextUse) {
					int healthChange = (int) Math.round((Math.random()*(maxHealthChange-minHealthChange)) + minHealthChange);
					player.sendMessage(message.replace("{HEALTHCHANGE}", String.valueOf(healthChange)));
					
					if (healthChange < 0) {
						player.decreaseHealth((-1) * healthChange);
					} else {
						player.increaseHealth(healthChange);
					}
				} else {
					player.sendMessage("You have to wait another " + Math.round((nextUse-System.currentTimeMillis())/1000) + " seconds to use me again!");
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
