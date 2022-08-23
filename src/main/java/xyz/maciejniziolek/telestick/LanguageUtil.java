package xyz.maciejniziolek.telestick;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class LanguageUtil {
    private static Map<String, Map<String, String>> messages = new HashMap<>();
    private static final String locales[] = { "en_us", "pl_pl", "de_de" };

    public static String getMessage(String key, String locale) {
        Map<String, String> map = messages.get(locales[0]);

        if(messages.containsKey(locale.toLowerCase()))
            map = messages.get(locale);

        if(map.containsKey(key))
            return map.get(key);

        return "?";
    }

    public static void init() {
        messages.clear();

        for(String locale : locales) {
            InputStream in = Telestick.getPluginInstance().getResource("locales/" + locale + ".yml");
            Reader reader = new InputStreamReader(in);
            FileConfiguration lang = YamlConfiguration.loadConfiguration(reader);

            Map<String, String> map = new HashMap<>();
            for(String key : lang.getKeys(false)) {
                for(String messKey : lang.getConfigurationSection(key).getKeys(false)) {
                    String mess = lang.getConfigurationSection(key).getString(messKey);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Telestick: " + locale + "." + messKey + ": " + mess);
                    map.put(messKey, mess);
                }
            }

            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Telestick: Locale " + locale + " has been loaded.");
            messages.put(locale, map);
        }
    }
}