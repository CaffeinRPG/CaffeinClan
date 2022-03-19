package tokyo.ramune.caffeinclan.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.caffeinclan.CaffeinClan;
import tokyo.ramune.caffeinclan.clan.Clan;
import tokyo.ramune.caffeinclan.clan.ClanManager;
import tokyo.ramune.caffeinclan.clan.ClanRole;
import tokyo.ramune.caffeinclan.database.SQL;
import tokyo.ramune.caffeinclan.database.SQLDate;

public class PlayerManager {

    public static void registerPlayer(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.insertData("uuid, status, latest_join_date, clan, clan_role", "'" + player.getUniqueId().toString() + "', 'true', '" + new SQLDate().getDateNow() + "', '', '" + ClanRole.MEMBER + "'", "players");
        });
    }

    public static void deletePlayer(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.deleteData("uuid", "=", player.getUniqueId().toString(), "players");
        });
    }

    public static void resetPlayer(Player player) {
        deletePlayer(player);
        registerPlayer(player);
    }

    public static boolean playerExists(Player player) {
        return SQL.exists("uuid", player.getUniqueId().toString(), "players");
    }

    public static PlayerStatus getPlayerStatus(Player player) {
        boolean status = Boolean.parseBoolean(String.valueOf(SQL.get("status", "uuid", "=", player.getUniqueId().toString(), "players")));
        SQLDate date = new SQLDate(String.valueOf(SQL.get("latest_join_date", "uuid", "=", player.getUniqueId().toString(), "players")));
        Clan clan = ClanManager.getClan(String.valueOf(SQL.get("clan", "uuid", "=", player.getUniqueId().toString(), "players")));
        ClanRole clanRole = ClanRole.valueOf(String.valueOf(SQL.get("clan_role", "uuid", "=", player.getUniqueId().toString(), "players")));
        return new PlayerStatus(player.getUniqueId(), status, date, clan, clanRole);
    }
}
