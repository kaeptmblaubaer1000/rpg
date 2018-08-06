package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

public class NPC extends BaseObject{
	public enum NPCType {TALKING, GIVING_ITEM};
	private NPCType type;
	private String message;
	private Item toGive;
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
	@Override
	public void onPlayerMove(Player player) {
		Vector2D ppos = player.getPosition();
        if ((Math.abs(ppos.x - position.x) == 1 && ppos.y == position.y) ||
                (Math.abs(ppos.y - position.y) == 1 && ppos.x == position.x)) {
			player.sendMessage(message);
			if (type == NPCType.GIVING_ITEM) {
				player.collectItem(toGive);
			}
		}
	}
	@Override
	public char render() {
		return '\u2638';
	}
}
