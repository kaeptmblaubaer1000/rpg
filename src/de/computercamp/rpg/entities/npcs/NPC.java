package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.*;
import de.computercamp.rpg.resources.Messages;

import java.text.MessageFormat;
import java.util.List;

public class NPC extends LivingBaseObject {
	protected long nextUse;
	protected Item requiredItem = null;
	protected MessageID npcMessageID;
	protected long delay;
	protected long despawn = 0;
	protected Player player;

	public NPC(Player player, Vector2D position, MessageID message, long delay) {
		super(position);
		npcMessageID = message;
		this.delay = delay;
		this.player = player;
	}

//	private static Vector2D getRandomLocation(Map map, Vector2D minPos, Vector2D maxPos) {
//		Vector2D randloc;
//		do {
//			int posX = (int) Math.round((Math.random() * (maxPos.x - minPos.x)) + minPos.x);
//			int posY = (int) Math.round((Math.random() * (maxPos.y - minPos.y)) + minPos.y);
//			randloc = new Vector2D(posX, posY);
//		} while (map.getObjectByPosition(randloc) != null);
//		return randloc;
//	}

	public void setRequiredItem(Item item) {
		requiredItem = item;
	}

	@Override
	public boolean onPlayerMove(Player player) {
		if (health <= 0) {
			return true;
		}
		String message;
		if (npcMessageID == null) {
			return false;
		}
		switch (npcMessageID) {
		case npcWelcome:
			message = Messages.npcWelcome;
			break;
		case npcMagician:
			message = Messages.npcMagician;
			break;
		case npcBadMagician:
			message = Messages.npcBadMagician;
			break;
		case npcWeaponsmith:
			message = Messages.npcWeaponsmith;
			break;
		case npcCook:
			message = Messages.npcCook;
			break;
		default:
			message = "Error: Message not found.";
		}
		if ((player.getPosition().x == position.x) && (player.getPosition().y == position.y)) {
			if (requiredItem != null) {
				if (!removeItemOfType(player, requiredItem)) {
					MessageFormat messageFormat = new MessageFormat(Messages.itemRequired, Messages.locale);
					player.sendMessage(messageFormat.format(new Object[] { requiredItem.getDisplayName() }));
					return false;
				}
			}
			requiredItem = null;
			if (System.currentTimeMillis() < nextUse) {
				MessageFormat messageFormat = new MessageFormat(Messages.npcWaiting, Messages.locale);
				player.sendMessage(messageFormat
						.format(new Object[] { Math.round((nextUse - System.currentTimeMillis()) / 1000) }));
				return false;
			} else if (nextUse == -1) {
				player.sendMessage(Messages.npcWaitingForever);
				return false;
			}
			player.sendMessage(message);
			doNPCAction(player);
			if (delay == -1)
				nextUse = -1;
			else
				nextUse = System.currentTimeMillis() + delay;

			return false;
		}
		return true;
	}

	protected boolean removeItemOfType(Player player, Item item) {
		List<Item> inv = player.getInventory();
		for (int i = 0; i != inv.size(); i++) {
			if (inv.get(i) instanceof Key && item instanceof Key) {
				player.removeItem(inv.get(i));
				return true;
			} else if (inv.get(i) instanceof Cucumber && item instanceof Cucumber) {
				player.removeItem(inv.get(i));
				return true;
			} else if (inv.get(i) instanceof HealingPotion && item instanceof HealingPotion) {
				player.removeItem(inv.get(i));
				return true;
			} else if (inv.get(i) instanceof SuicideSword && item instanceof SuicideSword) {
				player.removeItem(inv.get(i));
				return true;
			} else if (inv.get(i) instanceof Sword && item instanceof Sword) {
				player.removeItem(inv.get(i));
				return true;
			}
		}
		return false;
	}

	protected void doNPCAction(Player player) {
	}

	@Override
	public char render() {
		if (!isDead()) {
			return '\uA66A';
		} else {
			if (despawn == 0) {
				despawn = System.currentTimeMillis() + 5000;
			}
			if (System.currentTimeMillis() >= despawn && map.getMapContents().contains(this)) {
				if (Math.random() < 0.2) {
					Monster monster = new Monster(player, position, null, 0);
					synchronized (map) {
						map.addObject(monster);
					}
					monster.startFighting(player);
				}
				synchronized (map) {
					map.removeObject(this);
				}
				NPC me = this;
				Thread addThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
						}
						health = 20;
						nextUse = 0;
						despawn = 0;
						synchronized (player.getMap()) {
							player.getMap().addObject(me);
						}
					}
				});
				addThread.start();
			}

			return 'X';
		}
	}

	public void startMoving(Player player) {
		Thread rumlaufTimer = new Thread(new Runnable() {
			@Override
			public void run() {
				short richtung = (short) Math.round(Math.random() * 3);
				while (true) {
					Vector2D ziel = new Vector2D(0, 0);
					if (richtung == 0) {
						ziel = new Vector2D(position.x + 1, position.y);
					} else if (richtung == 1) {
						ziel = new Vector2D(position.x - 1, position.y);
					} else if (richtung == 2) {
						ziel = new Vector2D(position.x, position.y + 1);
					} else if (richtung == 3) {
						ziel = new Vector2D(position.x, position.y - 1);
					}

					if (map != null) {
						if (!((player.getPosition().x == position.x - 1 || player.getPosition().x == position.x + 1
								|| player.getPosition().x == position.x)
								&& (player.getPosition().y == position.y - 1 || player.getPosition().y == position.y + 1
										|| player.getPosition().y == position.y))) {
							if (map.getObjectByPosition(ziel) == null && !ziel.equals(player.getPosition())
									&& (ziel.x > 0 && ziel.y > 0 && ziel.x < 59 && ziel.y < 15)) {
								if (health > 0) {
									setPosition(ziel);
								}
							} else {
								richtung = (short) Math.round(Math.random() * 3);
							}
						}
					}

					try {
						Thread.sleep(Math.round(Math.random() * 5000));
					} catch (InterruptedException e) {
					}

				}

			}
		});

		rumlaufTimer.start();
	}

	public enum MessageID {
		npcWelcome, npcMagician, npcBadMagician, npcWeaponsmith, npcCook,

	}
}
