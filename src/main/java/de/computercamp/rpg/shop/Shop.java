package de.computercamp.rpg.shop;

import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Shop {

    private final Map<Item, Integer> offers = new HashMap<>();

    public void addOffer(Item item, int price) {
        offers.put(item, price);
    }

    public void removeOffer(Item item) {
        offers.remove(item);
    }

    public void buy(Player player, Item item) {
        player.removeCoins(offers.get(item));
        player.collectItem(item);
        offers.remove(item);
    }

    public String renderOffers() {
        StringBuilder string = new StringBuilder("Shop offers:\n");
        for (int i = 0; i < offers.size(); i++) {
            Item item = (Item) offers.keySet().toArray()[i];
            string.append(i).append(". ").append(item.getDisplayName()).append(": ").append(offers.get(item)).append(" Coins\n");
        }
        //for (Item item : offers.keySet()) {
        //    string.append(" ").append(item.getDisplayName()).append(": ").append(offers.get(item)).append(" Coins\n");
        //}
        string.deleteCharAt(string.length() - 1);
        return string.toString();
    }
}
