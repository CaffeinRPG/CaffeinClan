package tokyo.ramune.caffeinclan.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class GUIManager {

    public static Inventory getClanMenu() {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "§lクランメニュー");
        fillGrayStainedGrassPane(inv);
        inv.setItem(1, MenuItems.getBackGround());
        inv.setItem(10, MenuItems.getClose());
        return inv;
    }

    public static void fillGrayStainedGrassPane(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, MenuItems.getBackGround());
        }
    }
}
