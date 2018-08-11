package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;

public abstract class NPC extends LivingBaseObject {

    public NPC(Vector2D position) {
        super(position);
    }

    @Override
    public char render() {
        if (isDead()) {
            return 'X';
        }
        return '\uA66A';
    }

    protected abstract void doAction(Player player);

    public void startMoving(Player player) {
        Thread movingThread = new Thread(() -> {
            int direction = (short) Math.round(Math.random() * 3);
            while (true) {
                Vector2D target;
                switch (direction) {
                    case 0:
                        target = position.withX(position.x + 1);
                        break;
                    case 1:
                        target = position.withX(position.x - 1);
                        break;
                    case 2:
                        target = position.withY(position.y + 1);
                        break;
                    case 3:
                        target = position.withY(position.y - 1);
                        break;
                    default:
                        target = new Vector2D(0, 0);
                }

                if (map != null) {
                    if (!(
                            (player.getPosition().x == position.x - 1 || player.getPosition().x == position.x + 1 || player.getPosition().x == position.x) &&
                                    (player.getPosition().y == position.y - 1 || player.getPosition().y == position.y + 1 || player.getPosition().y == position.y)
                    )) {
                        if (map.getObjectByPosition(target) == null && !target.equals(player.getPosition()) &&
                                (target.x > 0 && target.y > 0 && target.x < 59 && target.y < 15)) {
                            if (health > 0) {
                                setPosition(target);
                            }
                        }
                        else {
                            direction = (int) Math.round(Math.random() * 3);
                        }
                    }
                }

                try {
                    Thread.sleep(Math.round(Math.random() * 5000));
                }
                catch (InterruptedException ignored) {
                }
            }
        }, getClass().getName() + "MovingThread");
        movingThread.start();
    }

    @Override
    public boolean onPlayerMove(Player player) {
        if (player.getPosition().equals(position)) {
            doAction(player);
            return false;
        }
        return true;
    }
}
