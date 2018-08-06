package de.computercamp.rpg;


public class Item extends BaseObject{
	
	public static enum Type {HEALING_POTION, SWORD};
	private Type type;
	private String symbol = "";
	private String displayName = "";
	public Item(Vector2D pos, Type type) {
		super(pos);
		this.type = type;
		if (type == Type.HEALING_POTION) {
			symbol = "o";
			displayName = "Healing potion";
		} else if (type == Type.SWORD) {
			symbol = "/";
			displayName = "Sword";
		} else {
			symbol = "?";
			displayName = "!UNKNOWN!";
		}
	}
	
	
	public Type getType() {
		return type;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getDisplayName() {
		return displayName;
	}
	

}
