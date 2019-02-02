package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.entities.items.Sword;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class WeaponSmithNPC extends NPC {

    private final Item item;
    private boolean usable = true;

    public WeaponSmithNPC(@NotNull Game game, @NotNull Vector2D position) {
        super(game, position);
        item = new Sword(game, new Vector2D(0, 0));
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
