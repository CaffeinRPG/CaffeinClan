package tokyo.ramune.caffeinclan.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.caffeinclan.CaffeinClan;
import tokyo.ramune.caffeinclan.clan.Member;
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

    public static ItemStack getCreateClan() {
        ItemStack item = new ItemStack(Material.JUNGLE_SIGN);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.DARK_AQUA + "新しいクランを作成");
        lore.add(ChatColor.BOLD + "新しいクランを作成をします");
        lore.add(ChatColor.BOLD + "クランを作成すると");
        lore.add(ChatColor.BOLD + "・" + ChatColor.GOLD + "クランクエスト");
        lore.add(ChatColor.BOLD + "・" + ChatColor.GOLD + "クランダンジョン");
        lore.add(ChatColor.BOLD + "・" + ChatColor.GOLD + "レイド戦");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "が可能となります");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "右クリックで作成");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public synchronized static ItemStack getClanInfo(Player player) {
        ItemStack item = new ItemStack(Material.JUNGLE_SIGN);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.WHITE + "§lランクinfo");
        try {
            lore.add(ChatColor.GREEN + "参加中のクラン" + ChatColor.WHITE + ": " + ChatColor.YELLOW + PlayerManager.getPlayerStatus(player).getClan().getName());
        } catch (Exception e) {
            lore.add(ChatColor.GREEN + "参加中のクラン" + ChatColor.WHITE + ": " + ChatColor.RED + "なし");
        }
        try {
            lore.add(ChatColor.BLUE + "参加中のメンバー" + ChatColor.WHITE + ": " + ChatColor.YELLOW + PlayerManager.getPlayerStatus(player).getClan().getMembers());
        } catch (Exception e) {
            lore.add(ChatColor.BLUE + "参加中のメンバー" + ChatColor.WHITE + ": " + ChatColor.RED + "なし");
        }
        try {
            int onlinePlayers = 0;
            for (Member member : PlayerManager.getPlayerStatus(player).getClan().getMembers()) {
                if (PlayerManager.getPlayerStatus(member.getPlayer()).getStatus()) {
                    onlinePlayers++;
                }
            }
            lore.add(ChatColor.GOLD + "オンラインのメンバー" + ChatColor.WHITE + ": " + ChatColor.YELLOW + onlinePlayers);
        } catch (Exception e) {
            lore.add(ChatColor.GOLD + "オンラインのメンバー" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "なし");
        }
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "§lクリックで詳細を表示");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
