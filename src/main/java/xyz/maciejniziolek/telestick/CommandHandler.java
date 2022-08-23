package xyz.maciejniziolek.telestick;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    public CommandHandler() {
        for(Command command : PluginCommandYamlParser.parse(Telestick.getPluginInstance()))
            Telestick.getPluginInstance().getCommand(command.getName()).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player player = (Player)sender;

        switch(label) {
            case "ts":
                GUI.open(player);
                return true;

            case "ts-version":
                player.sendMessage(ChatColor.YELLOW + LanguageUtil.getMessage("ts_version", player.getLocale()) + " " + Telestick.getPluginInstance().getDescription().getVersion());
                return true;
        }

        return false;
    }
}
