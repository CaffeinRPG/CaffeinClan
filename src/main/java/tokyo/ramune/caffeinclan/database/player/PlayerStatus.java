package tokyo.ramune.caffeinclan.database.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.caffeinclan.CaffeinClan;
import tokyo.ramune.caffeinclan.clan.Clan;
import tokyo.ramune.caffeinclan.clan.ClanRole;
import tokyo.ramune.caffeinclan.database.SQL;
import tokyo.ramune.caffeinclan.database.SQLDate;

import java.util.UUID;

public class PlayerStatus {

    private UUID uuid;
    private String username;
    private boolean status;
    private SQLDate latestJoinDate;
    private Clan clan;
    private ClanRole clanRole;

    public PlayerStatus(UUID uuid, String username, boolean status, SQLDate latestJoinDate, Clan clan, ClanRole clanRole) {
        this.uuid = uuid;
        this.username = username;
        this.status = status;
        this.latestJoinDate = latestJoinDate;
        this.clan = clan;
        this.clanRole = clanRole;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean isOnline() {
        return status;
    }

    public SQLDate getLatestJoinDate() {
        return latestJoinDate;
    }

    public Clan getClan() {
        return clan;
    }

    public ClanRole getClanRole() {
        return clanRole;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void setUsername(String username) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("username", username, "uuid", uuid.toString(), "players");
            this.username = username;
        });
    }

    public void setStatus(boolean status) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            if (status) {
                SQL.upsert("status", "true", "uuid", uuid.toString(), "players");
            } else {
                SQL.upsert("status", "false", "uuid", uuid.toString(), "players");
            }
            this.status = status;
        });
    }

    public void setLatestJoinDate(SQLDate latestJoinDate) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("latest_join_date", latestJoinDate.toString(), "uuid", uuid.toString(), "players");
            this.latestJoinDate = latestJoinDate;
        });
    }

    public void setClan(Clan clan) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("clan", clan.getName(), "uuid", uuid.toString(), "players");
            this.clan = clan;
        });
    }

    public void setClanRole(ClanRole clanRole) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("clan_role", clanRole, "uuid", uuid.toString(), "players");
            this.clanRole = clanRole;
        });
    }
}
