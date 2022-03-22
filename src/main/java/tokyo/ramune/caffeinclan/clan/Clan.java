package tokyo.ramune.caffeinclan.clan;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.caffeinclan.CaffeinClan;
import tokyo.ramune.caffeinclan.database.SQL;

import java.util.ArrayList;
import java.util.UUID;

public class Clan {

    private String name;

    public Clan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Member> getMembers() {
        ArrayList<Member> members = new ArrayList<>();
        for (String uuid : (String[]) SQL.listGet("clan", "uuid", "=", "", "").toArray()) {
            members.add(new Member(Bukkit.getPlayer(UUID.fromString(uuid)), ClanRole.valueOf(String.valueOf(SQL.get("clan_role", "uuid", "=", Bukkit.getPlayer(UUID.fromString(uuid)).getUniqueId().toString(), "players")))));
        }
        return members;
    }

    public void setName(String name) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("name", name, "name", name, "clans");
        });
    }

    public void addMember(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("clan", name, "uuid", player.getUniqueId().toString(), "players");
        });
    }

    public void removeMember(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            if (SQL.get("clan", "uuid", "=", player.getUniqueId().toString(), "players") == name) {
                SQL.upsert("clan", "", "uuid", player.getUniqueId().toString(), "players");
                SQL.upsert("clan_role", "MEMBER", "uuid", player.getUniqueId().toString(), "players");
            }
        });
    }

    public int getBank() {
        return Integer.valueOf(String.valueOf(SQL.get("bank", "name", "=", name, "clans")));
    }

    public void setBank(int bank) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("bank", String.valueOf(bank), "clan", name, "clans");
        });
    }
}
