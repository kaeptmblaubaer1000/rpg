package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;

public class BadMagicianNPC extends NPC {
    private int minChange;
    private int maxChange;

    private boolean usable = true;
    private int reuseSeconds;
    @NotNull
    private Timer timer;
    private long timerStarted;

    public BadMagicianNPC(@NotNull Game game, @NotNull Vector2D position, int reuseSeconds, int minChange, int maxChange) {
        super(game, position);
        this.minChange = minChange;
        this.maxChange = maxChange;
        this.reuseSeconds = reuseSeconds;
        timer = new Timer(true);
    }

    @Override
    protected void doAction(@NotNull Player player) {
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
            }, reuseSeconds * 1000);
            timerStarted = System.currentTimeMillis();
        } else {
            @NotNull
            MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
            int timePassed = (int) (System.currentTimeMillis() - timerStarted);
            @NotNull
            String message = messageFormat.format(new Object[]{reuseSeconds - timePassed / 1000});
            player.sendMessage(message);
        }
    }
}
