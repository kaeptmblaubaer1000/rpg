package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

public class NPC extends BaseObject{
    public enum NPCType {TALKING}

    private String message;
	public NPC(Vector2D position, String message) {
		super(position);
		this.message = message;
	}
	@Override
	public void onPlayerMove(Player player) {
		Vector2D ppos = player.getPosition();
        if ((Math.abs(ppos.x - position.x) == 1 && ppos.y == position.y) ||
                (Math.abs(ppos.y - position.y) == 1 && ppos.x == position.x)) {
			player.sendMessage(message);
		}
	}
	@Override
	public char render() {
		return '\u2638';
	}
}
