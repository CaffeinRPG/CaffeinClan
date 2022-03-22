package tokyo.ramune.caffeinclan.clan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import tokyo.ramune.caffeinclan.CaffeinClan;
import tokyo.ramune.caffeinclan.database.SQL;

import java.util.ArrayList;
import java.util.UUID;

public class ClanManager {

    public static void createClan(String name, Player master) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
        if (SQL.get("clan", "uuid", "=", master.getUniqueId().toString(), "players") != null) {
            master.sendMessage(ChatColor.RED + "クランの作成ができませんでした。 原因: あなたはすでにクランに参加済みです");
            return;
        }
        if (!(name.length() <= 20)) {
            master.sendMessage(ChatColor.RED + "クランの作成ができませんでした。 原因: クラン名は必ず20文字以下に設定してください");
            return;
        }
        if (SQL.exists("name", name, "clans")) {
            master.sendMessage(ChatColor.RED + "クランの作成ができませんでした。 原因: そのクラン名はすでに使われています");
            return;
        }
        SQL.insertData("name, master_uuid, bank, forgot_pay_count", "'" + name + "', '" + master.getUniqueId().toString() + "', 0, 0", "clans");
        master.sendMessage(ChatColor.GREEN + "クランを作成しました");
        });
    }

    public static void deleteClan(Player master, Clan clan) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            if (SQL.get("clan", "uuid", "=", master.getUniqueId().toString(), "players") != clan.getName()) {
                master.sendMessage(ChatColor.RED + "クランを削除できませんでした 原因: あなたはそのクランのメンバーではありません");
                return;
            }
            if (SQL.get("clan_role", "uuid", "=", master.getUniqueId().toString(), "players") != ClanRole.MASTER) {
                master.sendMessage(ChatColor.RED + "クランを削除できませんでした 原因: あなたはそのクランのマスターではありません");
                return;
            }
            SQL.deleteData("name", "=", clan.getName(), "clans");
            for (String uuid : (String[]) SQL.listGet("uuid", "clan", "=", clan.getName(), "players").toArray()) {
                SQL.upsert("clan", "", "uuid", uuid, "players");
                SQL.upsert("clan_role", "MEMBER", "uuid", uuid, "players");
            }
            master.sendMessage(ChatColor.GREEN + "あなたのクランは削除されました");
        });
    }

    public static void setName(Player master,Clan clan, String name) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            if (SQL.get("clan", "uuid", "=", master.getUniqueId().toString(), "players") != name) {
                master.sendMessage(ChatColor.RED + "名前を変更できませんでした 原因: あなたはそのクランのメンバーではありません");
                return;
            }
            if (SQL.get("clan_role", "uuid", "=", master.getUniqueId().toString(), "players") != ClanRole.MASTER) {
                master.sendMessage(ChatColor.RED + "名前を変更できませんでした 原因: あなたはクランのマスターではありません");
                return;
            }
            SQL.upsert("name", name, "name", clan.getName(), "clans");
            master.sendMessage(ChatColor.GREEN + "クラン名を変更しました");
        });
    }

    public static Clan getClan(String name) {
        if (SQL.exists("name", name, "clans")) {
            return new Clan(name);
        } else {
            return null;
        }
    }

    public static ArrayList<Clan> getClanList() {
        ArrayList<Clan> clans = new ArrayList<>();
        for (String clanName : (String[]) SQL.listGet("name", "name", "!=", "***************", "clans").toArray()) {
            clans.add(getClan(clanName));
        }
        return clans;
    }
}