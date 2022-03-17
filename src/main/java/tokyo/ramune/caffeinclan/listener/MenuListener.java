package tokyo.ramune.caffeinclan.listener;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onMoveItem(InventoryClickEvent e) {

        if (e.getView().getTitle().equals(ChatColor.DARK_GREEN + "§lクランメニュー")) {
            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);

            if (e.getClick().equals(ClickType.DOUBLE_CLICK) || e.getClick().equals(ClickType.SHIFT_LEFT)) {
                return;
            }
            player.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 1, Float.valueOf(2));
        }

    }
}
