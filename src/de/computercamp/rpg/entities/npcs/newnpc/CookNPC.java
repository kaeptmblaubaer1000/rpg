package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.entities.items.Key;
import de.computercamp.rpg.resources.Messages;

import java.util.Timer;
import java.util.TimerTask;

public class CookNPC extends NPC {

    private boolean gotItem;

    private boolean usable = true;
    private int reuseSeconds;
    private Timer timer;

    public CookNPC(Vector2D position, int reuseSeconds, int minChange, int maxChange) {
        super(position);
        this.reuseSeconds = reuseSeconds;
        timer = new Timer(true);
    }

    @Override
    protected void doAction(Player player) {
        Item requiredItem = new Key(null);
        if (player.getInventory().contains(requiredItem)) {
            player.removeItem(requiredItem);
            gotItem = true;
        } else {
            //TODO: MessageFormat
            player.sendMessage(Messages.itemRequired);
        }

        if (usable) {

            usable = false;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    usable = true;
                }
            }, reuseSeconds);
        } else {
            player.sendMessage(Messages.npcWaiting);
        }
    }
}
