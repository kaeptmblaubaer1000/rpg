package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

public class NPC extends BaseObject{
	public enum NPCType {TALKING};
	String message;
	public NPC(Vector2D position, String message) {
		super(position);
		this.message = message;
	}
	@Override
	public void onPlayerMove(Player player) {
		Vector2D ppos = player.getPosition();
		if ((Math.abs(ppos.x-getPosition().x) == 1 && ppos.y == getPosition().y) || 
				(Math.abs(ppos.y-getPosition().y) == 1 && ppos.x == getPosition().x)){
			System.out.println(message);
		}
	}
	@Override
	public char render() {
		// TODO Auto-generated method stub
		return '\u2638';
	}
}
