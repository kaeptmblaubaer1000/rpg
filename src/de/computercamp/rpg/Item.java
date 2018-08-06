package de.computercamp.rpg;


public class Item {
	private Vector2D pos;
	public static enum Type {HEALING_POTION, SWORD};
	private Type type;
	private String symbol = "";
	private String displayname = "";
	public Item(Vector2D pos, Type type) {
		this.pos = pos;
		this.type = type;
		if (type == Type.HEALING_POTION) {
			symbol = "o";
			displayname = "Healing potion";
		} else if (type == Type.SWORD) {
			symbol = "/";
			displayname = "Sword";
		} else {
			symbol = "?";
			displayname = "!UNKNOWN!";
		}
	}
	public Vector2D getPos() {
		return pos;
	}
	public void setPos(Vector2D pos) {
		this.pos = pos;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getDisplayname() {
		return displayname;
	}
	

}
