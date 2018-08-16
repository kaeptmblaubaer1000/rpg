package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.entities.items.Sword;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class WeaponSmithNPC extends NPC {

    private Item item;
    private boolean usable = true;

    public WeaponSmithNPC(Vector2D position) {
        super(position);
        item = new Sword(null);
    }

    @Override
    public void onHealthChange() {
        super.onHealthChange();
        if (isDead() && usable) {
            item.setPosition(getPosition());
            getMap().addObject(item);
        }
    }

    @Override
    protected void doAction(@NotNull Player player) {
        if (usable) {
            player.sendMessage(Messages.npcWeaponsmith);
            player.collectItem(item);
            usable = false;
        } else {
            player.sendMessage(Messages.npcWaitingForever);
        }
    }
}
