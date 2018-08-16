package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class Sword extends Item {

    public Sword(@NotNull Game game, @NotNull Vector2D position) {
        super(game, position);
        symbol = '\u2e38';
    }

    @Override
    public boolean use(@NotNull Player player) {
        if (player.getMap().getObjectByPosition(player.getPosition(), false) instanceof LivingBaseObject) {
            ((LivingBaseObject) player.getMap().getObjectByPosition(player.getPosition(), false)).decreaseHealth(10);
        } else if (player.getMap().getObjectByPosition(player.getPosition().withY(player.getPosition().y + 1), false) instanceof LivingBaseObject) {
            ((LivingBaseObject) player.getMap().getObjectByPosition(player.getPosition().withY(player.getPosition().y + 1), false)).decreaseHealth(10);
        } else if (player.getMap().getObjectByPosition(player.getPosition().withY(player.getPosition().y - 1), false) instanceof LivingBaseObject) {
            ((LivingBaseObject) player.getMap().getObjectByPosition(player.getPosition().withY(player.getPosition().y - 1), false)).decreaseHealth(10);
        } else if (player.getMap().getObjectByPosition(player.getPosition().withX(player.getPosition().x + 1), false) instanceof LivingBaseObject) {
            ((LivingBaseObject) player.getMap().getObjectByPosition(player.getPosition().withX(player.getPosition().x + 1), false)).decreaseHealth(10);
        } else if (player.getMap().getObjectByPosition(player.getPosition().withX(player.getPosition().x - 1), false) instanceof LivingBaseObject) {
            ((LivingBaseObject) player.getMap().getObjectByPosition(player.getPosition().withX(player.getPosition().x - 1), false)).decreaseHealth(10);
        }
        return false;
    }

    @Override
    public String getDisplayName() {
        return Messages.sword;
    }
}
