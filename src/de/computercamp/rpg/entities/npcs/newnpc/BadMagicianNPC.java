package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

import java.util.Timer;
import java.util.TimerTask;

public class BadMagicianNPC extends NPC {

    private int minChange;
    private int maxChange;

    private boolean usable = true;
    private int reuseSeconds;
    private Timer timer;

    public BadMagicianNPC(Vector2D position, int reuseSeconds, int minChange, int maxChange) {
        super(position);
        this.minChange = minChange;
        this.maxChange = maxChange;
        this.reuseSeconds = reuseSeconds;
        timer = new Timer(true);
    }

    @Override
    protected void doAction(Player player) {
        if (usable) {
            player.sendMessage(Messages.npcBadMagician);
            int change = (int) Math.round((Math.random() * (maxChange - minChange)) + minChange);
            if (change < 0) {
                player.decreaseHealth((-1) * change);
            } else {
                player.increaseHealth(change);
            }
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
