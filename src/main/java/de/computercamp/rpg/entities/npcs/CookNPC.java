package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Cucumber;
import de.computercamp.rpg.resources.Messages;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;

public class CookNPC extends NPC {

    private int requiredCoins;

    private boolean usable = true;
    private int reuseSeconds;
    private Timer timer;
    private long timerStarted;

    public CookNPC(Vector2D position, int reuseSeconds, int requiredCoins) {
        super(position);
        this.reuseSeconds = reuseSeconds;
        this.requiredCoins = requiredCoins;
        timer = new Timer(true);
    }

    @Override
    protected void doAction(Player player) {
        if (usable) {
            if (player.getCoins() >= requiredCoins) {
                player.removeCoins(requiredCoins);
                player.sendMessage(Messages.npcCook);
                player.collectItem(new Cucumber(null));
                usable = false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        usable = true;
                    }
                }, reuseSeconds * 1000);
                timerStarted = System.currentTimeMillis();
            }
            else {
                MessageFormat messageFormat = new MessageFormat(Messages.coinsRequired, Messages.locale);
                String message = messageFormat.format(new Object[]{requiredCoins});
                player.sendMessage(message);
            }
        }
        else {
            MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
            int timePassed = (int) (System.currentTimeMillis() - timerStarted);
            String message = messageFormat.format(new Object[]{reuseSeconds - timePassed / 1000});
            player.sendMessage(message);
        }
    }
}
