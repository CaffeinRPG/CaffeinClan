package tokyo.ramune.caffeinclan.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.caffeinclan.database.SQL;
import tokyo.ramune.caffeinclan.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class MenuItems {

    public static ItemStack getBackGround() {
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(" ");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getClose() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.RED + "❌ 閉じる");
        lore.add(ChatColor.GREEN + "このメニューを閉じます");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getBack() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.RED + "◀ 戻る");
        lore.add(ChatColor.GREEN + "前のメニューに戻ります");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getClanInfo(Player player) {
        ItemStack item = new ItemStack(Material.JUNGLE_SIGN);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.BLUE + "ランク info");
        try {
            lore.add(ChatColor.GREEN + "参加中のクラン" + ChatColor.WHITE + ": " + ChatColor.YELLOW + PlayerManager.getPlayerStatus(player).getClan().getName());
        } catch (Exception e) {
            lore.add(ChatColor.GREEN + "参加中のクラン" + ChatColor.WHITE + ": " + ChatColor.RED + "なし");
        }
        return null;
    }
}
