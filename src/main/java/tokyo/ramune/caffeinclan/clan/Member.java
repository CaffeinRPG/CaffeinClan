package tokyo.ramune.caffeinclan.clan;

import org.bukkit.entity.Player;

public class Member {

    private Player player;
    private ClanRole role;

    public Member(Player player, ClanRole role) {
        this.player = player;
        this.role = role;
    }

    public Player getPlayer() {
        return player;
    }

    public ClanRole getRole() {
        return role;
    }

    public void setRole(ClanRole role) {
        this.role = role;
    }
}
