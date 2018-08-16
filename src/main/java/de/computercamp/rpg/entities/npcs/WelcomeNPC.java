package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class WelcomeNPC extends NPC {

    public WelcomeNPC(Vector2D position) {
        super(position);
    }

    @Override
    protected void doAction(@NotNull Player player) {
        player.sendMessage(Messages.npcWelcome);
    }
}
