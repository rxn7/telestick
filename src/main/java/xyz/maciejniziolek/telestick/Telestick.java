package xyz.maciejniziolek.telestick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Telestick extends JavaPlugin {
    private static JavaPlugin pluginInstance;

    @Override
    public void onEnable() {
        pluginInstance = this;
        LanguageUtil.init();

        getServer().getPluginManager().registerEvents(new GUIEventHandler(), this);
        new CommandHandler();

        Bukkit.broadcastMessage(ChatColor.GREEN + "Telestick plugin has been loaded");
    }

    @Override
    public void onDisable() {
    }

    public static JavaPlugin getPluginInstance() {
        return pluginInstance;
    }
}
