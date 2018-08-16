package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Coin;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ghost extends LivingBaseObject {

    private Random random = new Random();
    private boolean despawned = false;

    public Ghost(Vector2D position) {
        super(position);
    }

    public void startFighting(Player player) {
        Thread fightThread = new Thread(() -> {
            while (true) {
                if (health > 0) {
                    boolean move = true;

                    if (
                        player.equals(getMap().getObjectByPosition(getPosition().withX(getPosition().x + 1), true)) ||
                            player.equals(getMap().getObjectByPosition(getPosition().withX(getPosition().x - 1), true)) ||
                            player.equals(getMap().getObjectByPosition(getPosition().withY(getPosition().y + 1), true)) ||
                            player.equals(getMap().getObjectByPosition(getPosition().withY(getPosition().y - 1), true))
                    ) {
                        move = false;
                    }

                    if (move) { //Move
                        if (getPosition().y > player.getPosition().y) {
                            up();
                        } else if (getPosition().y < player.getPosition().y) {
                            down();
                        } else if (getPosition().x > player.getPosition().x) {
                            left();
                        } else if (getPosition().x < player.getPosition().x) {
                            right();
                        }
                    } else { //Hit
                        player.decreaseHealth(random.nextInt(5) + 5);
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                } else {
                    break;
                }
            }
        });
        fightThread.start();
    }

    @Override
    public void onHealthChange() {
        if (isDead() && !despawned) {
            Timer despawnTimer = new Timer(true);
            despawnTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    getMap().removeObject(Ghost.this);
                }
            }, 5000);
            getMap().addObject(new Coin(getPosition()));
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
