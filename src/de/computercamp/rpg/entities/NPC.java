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
		if ((Math.abs(ppos.x-getPosition().x) == 1 && ppos.y == getPosition().y) || 
				(Math.abs(ppos.y-getPosition().y) == 1 && ppos.x == getPosition().x)){
			player.sendMessage(message);
			if (type == NPCType.GIVING_ITEM) {
				player.collectItem(toGive);
			}
		}
	}
	@Override
	public char render() {
		// TODO Auto-generated method stub
		return '\u2638';
	}
}
