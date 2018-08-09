package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class Sword extends Item {

    public Sword(Vector2D position) {
        super(position);
        symbol = '\u2e38';
    }

    @Override
    public boolean use(Player player) {
        if (player.getMap().getObjectByPosition(position.withY(position.y + 1)) != null && player.getMap().getObjectByPosition(position.withY(position.y + 1)) instanceof LivingBaseObject) {
            ((LivingBaseObject)player.getMap().getObjectByPosition(position.withY(position.y + 1))).decreaseHealth(10);
        } else if (player.getMap().getObjectByPosition(position.withY(position.y - 1)) != null && player.getMap().getObjectByPosition(position.withY(position.y - 1)) instanceof LivingBaseObject) {
            ((LivingBaseObject)player.getMap().getObjectByPosition(position.withY(position.y - 1))).decreaseHealth(10);
        } else if (player.getMap().getObjectByPosition(position.withX(position.x + 1)) != null && player.getMap().getObjectByPosition(position.withX(position.x + 1)) instanceof LivingBaseObject) {
            ((LivingBaseObject)player.getMap().getObjectByPosition(position.withX(position.x + 1))).decreaseHealth(10);
        } else if (player.getMap().getObjectByPosition(position.withX(position.x - 1)) != null && player.getMap().getObjectByPosition(position.withX(position.x - 1)) instanceof LivingBaseObject) {
            ((LivingBaseObject)player.getMap().getObjectByPosition(position.withX(position.x - 1))).decreaseHealth(10);
        }
        return false;
    }

    @Override
    public String getDisplayName() {
        return Messages.sword;
    }
}
