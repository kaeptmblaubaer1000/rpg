package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class WelcomeNPC extends NPC {

    public WelcomeNPC(Vector2D position) {
        super(position);
    }

    @Override
    protected void doAction(Player player) {
        player.sendMessage(Messages.npcWelcome);
    }
}
