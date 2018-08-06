package de.computercamp.rpg;


public class Item extends BaseObject{
	
	public static enum Type {HEALING_POTION, SWORD};
	private Type type;
	private String symbol = "";
	private String displayname = "";
	public Item(Vector2D pos, Type type) {
		super(pos);
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
