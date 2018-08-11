package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Sword;
import de.computercamp.rpg.resources.Messages;

public class WeaponSmithNPC extends NPC {

    private boolean used = false;

    public WeaponSmithNPC(Vector2D position) {
        super(position);
    }

    @Override
    protected void doAction(Player player) {
        if (!used) {
            player.sendMessage(Messages.npcWeaponsmith);
            player.collectItem(new Sword(null));
            used = true;
        } else {
            player.sendMessage(Messages.npcWaitingForever);
        }
    }
}
