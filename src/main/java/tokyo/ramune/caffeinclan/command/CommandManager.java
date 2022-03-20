package tokyo.ramune.caffeinclan.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.caffeinclan.gui.GUIManager;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        ((Player) sender).openInventory(GUIManager.getClanMenu((Player) sender));
        return true;
    }
}
