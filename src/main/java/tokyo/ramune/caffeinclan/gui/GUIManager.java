package tokyo.ramune.caffeinclan.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.caffeinclan.CaffeinClan;

public class GUIManager {

    public static Inventory getClanMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "§lクランメニュー");
        player.openInventory(inv);
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            fillGrayStainedGrassPane(inv);
            inv.setItem(1, MenuItems.getBackGround());
            inv.setItem(10, MenuItems.getClose());
            inv.setItem(20, MenuItems.getClanInfo(player));
        });
        return inv;
    }

    public static void fillGrayStainedGrassPane(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, MenuItems.getBackGround());
        }
    }
}
