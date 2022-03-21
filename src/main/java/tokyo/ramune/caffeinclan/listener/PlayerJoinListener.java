package tokyo.ramune.caffeinclan.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.caffeinclan.CaffeinClan;
import tokyo.ramune.caffeinclan.database.SQLDate;
import tokyo.ramune.caffeinclan.player.PlayerManager;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!PlayerManager.playerExists(e.getPlayer())) {
            PlayerManager.registerPlayer(e.getPlayer());
        }
        try {
            PlayerManager.getPlayerStatus(e.getPlayer()).setStatus(true);
        } catch (Exception ignored) {
        }
        Bukkit.getScheduler().runTaskLater(CaffeinClan.getInstance(), () -> {
            if (PlayerManager.getPlayerStatus(e.getPlayer()).getLatestJoinDate() != new SQLDate()) {
                PlayerManager.getPlayerStatus(e.getPlayer()).setLatestJoinDate(new SQLDate());
            }
        }, 10);
    }
}
