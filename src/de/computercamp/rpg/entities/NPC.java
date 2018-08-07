package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

public class NPC extends BaseObject{
    public enum NPCType {TALKING, GIVING_ITEM, HEALTH_CHANGING}

    private NPCType type;
	private String message;
	private Item toGive;
	private boolean healingOrKilling;
	public NPC(Vector2D position, String message) {
		super(position);
		this.message = message;
		type = NPCType.TALKING;
	}
	public NPC(Vector2D position, String message, Item toGive) {
		super(position);
		this.message = message;
		type = NPCType.GIVING_ITEM;
		this.toGive = toGive;
	}
	public NPC(Vector2D position, String message, boolean healingOrKilling) {
		super(position);
		this.message = message;
		type = NPCType.HEALTH_CHANGING;
		this.healingOrKilling = healingOrKilling;
	}
	@Override
    public boolean onPlayerMove(Player player) {
		Vector2D ppos = player.getPosition();
        if ((Math.abs(ppos.x - position.x) == 1 && ppos.y == position.y) ||
                (Math.abs(ppos.y - position.y) == 1 && ppos.x == position.x)) {
			player.sendMessage(message);
			if (type == NPCType.GIVING_ITEM) {
				player.collectItem(toGive);
			} else if (type == NPCType.HEALTH_CHANGING) {
				if (healingOrKilling == true) {
					player.increaseHealth(Player.MAX_HEALTH-player.getHealth());
				} else {
					player.decreaseHealth(player.getHealth());
				}
			}
		}
	}
	@Override
	public char render() {
		return '\u2638';
	}
}
