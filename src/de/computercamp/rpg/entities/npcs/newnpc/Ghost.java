package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Ghost extends LivingBaseObject {

    private boolean despawned = false;

    public Ghost(Vector2D position) {
        super(position);
    }

    public void startFighting(Player player, Map map) {
        Thread fightThread = new Thread(() -> {
            while (true) {
                if (health > 0) {
                    if (Math.abs(player.getPosition().x - position.x) > 1) {
                        if (player.getPosition().x < position.x) {
                            if (!(map.getObjectByPosition(new Vector2D(position.x - 1, position.y)) instanceof LivingBaseObject)) {
                                setPosition(new Vector2D(position.x - 1, position.y));
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x - 1, position.y))).getHealth() <= 0) {
                                setPosition(new Vector2D(position.x - 1, position.y));
                            }
                        }
                        else {
                            if (!(map.getObjectByPosition(new Vector2D(position.x + 1, position.y)) instanceof LivingBaseObject)) {
                                setPosition(new Vector2D(position.x + 1, position.y));
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x + 1, position.y))).getHealth() <= 0) {
                                setPosition(new Vector2D(position.x + 1, position.y));
                            }
                        }
                    }
                    else if (Math.abs(player.getPosition().y - position.y) > 0) {
                        if (player.getPosition().y < position.y) {
                            if (!(map.getObjectByPosition(new Vector2D(position.x, position.y - 1)) instanceof LivingBaseObject)) {
                                setPosition(new Vector2D(position.x, position.y - 1));
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x, position.y - 1))).getHealth() <= 0) {
                                setPosition(new Vector2D(position.x, position.y - 1));
                            }
                        }
                        else {
                            if (!(map.getObjectByPosition(new Vector2D(position.x, position.y + 1)) instanceof LivingBaseObject)) {
                                setPosition(new Vector2D(position.x, position.y + 1));
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x, position.y + 1))).getHealth() <= 0) {
                                setPosition(new Vector2D(position.x, position.y + 1));
                            }
                        }
                    }
                    else {
                        player.decreaseHealth(1);
                    }

                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ignored) {
                    }
                }
            }
        });
        fightThread.start();
    }

    @Override
    public void onHealthChanged() {
        if (health <= 0 && !despawned) {
            Timer despawnTimer = new Timer(true);
            despawnTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    map.removeObject(Ghost.this);
                }
            }, 5000);
            despawned = true;
        }
    }

    @Override
    public char render() {
        if (isDead()) {
            return 'X';
        }
        return '\u2e0e';
    }
}
