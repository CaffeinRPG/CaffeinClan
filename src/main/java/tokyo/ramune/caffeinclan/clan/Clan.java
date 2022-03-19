package tokyo.ramune.caffeinclan.clan;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.caffeinclan.CaffeinClan;
import tokyo.ramune.caffeinclan.database.SQL;

import java.util.ArrayList;

public class Clan {

    private String name;
    private ArrayList<Member> members;
    private int bank;

    public Clan(String name, ArrayList<Member> members, int bank) {
        this.name = name;
        this.members = members;
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public int getBank() {
        return bank;
    }

    public void setName(String name) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("name", name, "name", name, "clans");
        });
    }

    public void addMember(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("clan", name, "uuid", player.getUniqueId().toString(), "players");
            members.add(new Member(player, ClanRole.MEMBER));
        });
    }

    public void removeMember(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            if (SQL.get("clan", "uuid", "=", player.getUniqueId().toString(), "players") == name) {
                SQL.upsert("clan", "", "uuid", player.getUniqueId().toString(), "players");
                SQL.upsert("clan_role", "MEMBER", "uuid", player.getUniqueId().toString(), "players");
                members.remove(new Member(player, ClanRole.valueOf(String.valueOf(SQL.get("clan_role", "uuid", "=", player.getUniqueId().toString(), "players")))));
            }
        });
    }

    public void setBank(int bank) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("bank", String.valueOf(bank), "clan", name, "clans");
            this.bank = bank;
        });
    }

    public void depositBank(int bank) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("bank", String.valueOf(bank + this.bank), "clan", name, "clans");
            this.bank = this.bank + bank;
        });
    }

    public void paymentBank(int bank) {
        Bukkit.getScheduler().runTaskAsynchronously(CaffeinClan.getInstance(), () -> {
            SQL.upsert("bank", String.valueOf(this.bank - bank), "clan", name, "clans");
            this.bank = bank - this.bank;
        });
    }

    public void paymentMaintenanceCosts() {
    }
}
