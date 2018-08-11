package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.HealingPotion;
import de.computercamp.rpg.resources.Messages;

import java.util.Timer;
import java.util.TimerTask;

public class MagicianNPC extends NPC {

    private boolean usable = true;
    private int reuseSeconds;
    private Timer timer;

    public MagicianNPC(Vector2D position, int reuseSeconds) {
        super(position);
        this.reuseSeconds = reuseSeconds;
        timer = new Timer(true);
    }

    @Override
    protected void doAction(Player player) {
        if (usable) {
            player.sendMessage(Messages.npcMagician);
            player.collectItem(new HealingPotion(null));
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
