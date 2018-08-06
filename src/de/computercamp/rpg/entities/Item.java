package de.computercamp.rpg.entities;


import de.computercamp.rpg.Vector2D;

public class Item extends BaseObject{

	public enum Type {HEALING_POTION, SWORD, KEY}

	private Type type;
	private char symbol;
	private String displayName;
	public Item(Vector2D pos, Type type) {
		super(pos);
		this.type = type;
		if (type == Type.HEALING_POTION) {
			symbol = '\u1e50';
			displayName = "Healing potion";
		} else if (type == Type.SWORD) {
			symbol = '\u1e36';
			displayName = "Sword";
		} else if (type == Type.KEY) {
			symbol = '\ua720';
			displayName = "Key";
		}else {
			symbol = 'E';
			displayName = "!UNKNOWN!";
		}
	}
	
	
	public Type getType() {
		return type;
	}
	public char getSymbol() {
		return symbol;
	}
	public String getDisplayName() {
		return displayName;
	}


	@Override
	public char render() {
		return symbol;
	}
	

}
