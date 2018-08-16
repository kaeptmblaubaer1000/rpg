package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.HealingPotion;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MagicianNPC extends NPC {

    private boolean usable = true;
    private Item item;

    private int reuseSeconds;
    private Timer timer;
    private long timerStarted;

    public MagicianNPC(@NotNull Game game, @NotNull Vector2D position, int reuseSeconds) {
        super(game, position);
        item = new HealingPotion(game, new Vector2D(1, 1));
        this.reuseSeconds = reuseSeconds;
        timer = new Timer(true);
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
            player.sendMessage(Messages.npcMagician);
            player.collectItem(item);
            usable = false;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    usable = true;
                }
            }, reuseSeconds * 1000);
            timerStarted = System.currentTimeMillis();
        } else {
            MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
            int timePassed = (int) (System.currentTimeMillis() - timerStarted);
            String message = messageFormat.format(new Object[]{reuseSeconds - timePassed / 1000});
            player.sendMessage(message);
        }
    }
}
