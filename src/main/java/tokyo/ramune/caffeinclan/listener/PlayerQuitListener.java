package tokyo.ramune.caffeinclan.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.caffeinclan.database.player.PlayerManager;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PlayerManager.getPlayerStatus(e.getPlayer()).setStatus(false);
    }
}
