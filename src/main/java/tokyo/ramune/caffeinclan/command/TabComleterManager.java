package tokyo.ramune.caffeinclan.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabComleterManager implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        StringUtil.copyPartialMatches(args[0], Collections.singleton("menu"), completions);

        Collections.sort(completions);
        return completions;
    }
}
