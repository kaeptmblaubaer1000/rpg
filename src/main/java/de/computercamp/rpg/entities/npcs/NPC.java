package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public abstract class NPC extends LivingBaseObject {

    private boolean despawned = false;

    public NPC(@NotNull Game game, @NotNull Vector2D position) {
        super(game, position);
    }

    @Override
    public char render() {
        if (isDead()) {
            return 'X';
        }
        return '\uA66B';
    }

    protected abstract void doAction(@NotNull Player player);

    public void startMoving(@NotNull Player player) {
        Thread movingThread = new Thread(() -> {
            int direction = (short) Math.round(Math.random() * 3);
            while (true) { // TODO: try to make this a Timer or something else
                Vector2D target;
                switch (direction) {
                case 0:
                    target = getPosition().withX(getPosition().x + 1);
                    break;
                case 1:
                    target = getPosition().withX(getPosition().x - 1);
                    break;
                case 2:
                    target = getPosition().withY(getPosition().y + 1);
                    break;
                case 3:
                    target = getPosition().withY(getPosition().y - 1);
                    break;
                default:
                    target = new Vector2D(0, 0);
                }

                if (!(
                    (player.getPosition().x == getPosition().x - 1 || player.getPosition().x == getPosition().x + 1 || player.getPosition().x == getPosition().x) &&
                        (player.getPosition().y == getPosition().y - 1 || player.getPosition().y == getPosition().y + 1 || player.getPosition().y == getPosition().y)
                )) {
                    if (getMap().getObjectByPosition(target, false) == null && !target.equals(player.getPosition()) &&
                        (target.x > 0 && target.y > 0 && target.x < 59 && target.y < 15)) {
                        if (health > 0) {
                            setPosition(target);
                        }
                    } else {
                        direction = (int) Math.round(Math.random() * 3);
                    }
                }

                try {
                    Thread.sleep(Math.round(Math.random() * 5000));
                } catch (InterruptedException ignored) {
                }
            }
        });
        movingThread.start();
    }

    @Override
    public void onHealthChange() {
        if (isDead() && !despawned) {
            Timer despawnTimer = new Timer(true);
            despawnTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (Math.random() < 0.2) {
                        Ghost ghost = new Ghost(getGame(), getPosition());
                        getMap().addObject(ghost);
                        Player player = null;
                        for (BaseObject object : getMap().getMapContents()) {
                            if (object instanceof Player) {
                                player = (Player) object;
                                break;
                            }
                        }
                        ghost.startFighting(Objects.requireNonNull(player));
                    }
                    getMap().removeObject(NPC.this);
                }
            }, 5000);
            despawned = true;
        }
    }

    @Override
    public boolean onPlayerMove(@NotNull Player player) {
        if (player.getPosition().equals(getPosition()) && !isDead()) {
            doAction(player);
            return false;
        }
        return true;
    }
}
