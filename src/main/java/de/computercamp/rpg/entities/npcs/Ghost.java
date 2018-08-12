package de.computercamp.rpg.entities.npcs;

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
                                left();
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x - 1, position.y))).getHealth() <= 0) {
                                left();
                            }
                        }
                        else {
                            if (!(map.getObjectByPosition(new Vector2D(position.x + 1, position.y)) instanceof LivingBaseObject)) {
                                right();
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x + 1, position.y))).getHealth() <= 0) {
                                right();
                            }
                        }
                    }
                    else if (Math.abs(player.getPosition().y - position.y) > 0) {
                        if (player.getPosition().y < position.y) {
                            if (!(map.getObjectByPosition(new Vector2D(position.x, position.y - 1)) instanceof LivingBaseObject)) {
                                up();
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x, position.y - 1))).getHealth() <= 0) {
                                up();
                            }
                        }
                        else {
                            if (!(map.getObjectByPosition(new Vector2D(position.x, position.y + 1)) instanceof LivingBaseObject)) {
                                down();
                            }
                            else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x, position.y + 1))).getHealth() <= 0) {
                                down();
                            }
                        }
                    }
                    else {
                        player.decreaseHealth(5);
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

        //Thread fightThread = new Thread(() -> {
        //    while (true) {
        //        if (health > 0) {
        //            boolean move = true;
        //
        //            if (position.equals(player.getPosition())) {
        //                move = false;
        //            }
        //
        //            if (move) { //Move
        //                if (player.getPosition().x < position.x) {
        //                    left();
        //                }
        //                else if (player.getPosition().x > position.x) {
        //                    right();
        //                }
        //                else if (player.getPosition().y < position.y) {
        //                    up();
        //                }
        //                else if (player.getPosition().y > position.y) {
        //                    down();
        //                }
        //            }
        //            else { //Hit
        //                if (getMap().getObjectByPosition(getPosition().withY(getPosition().y + 1)).equals(player)) {
        //                    player.decreaseHealth(10);
        //                }
        //                else if (getMap().getObjectByPosition(getPosition().withY(getPosition().y - 1)).equals(player)) {
        //                    player.decreaseHealth(10);
        //                }
        //                else if (getMap().getObjectByPosition(getPosition().withX(getPosition().x + 1)).equals(player)) {
        //                    player.decreaseHealth(10);
        //                }
        //                else if (getMap().getObjectByPosition(getPosition().withX(getPosition().x - 1)).equals(player)) {
        //                    player.decreaseHealth(10);
        //                }
        //            }
        //
        //            try {
        //                Thread.sleep(500);
        //            }
        //            catch (InterruptedException ignored) {}
        //        }
        //    }
        //});
        //fightThread.run();
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
