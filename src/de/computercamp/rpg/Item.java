package de.computercamp.rpg;


public class Item extends BaseObject{
	
	public static enum Type {HEALING_POTION, SWORD};
	private Type type;
	private char symbol;
	private String displayName;
	public Item(Vector2D pos, Type type) {
		super(pos);
		this.type = type;
		if (type == Type.HEALING_POTION) {
			symbol = 'ṑ';
			displayName = "Healing potion";
		} else if (type == Type.SWORD) {
			symbol = 'ḷ';
			displayName = "Sword";
		} else {
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
		// TODO Auto-generated method stub
		return symbol;
	}
	

}
