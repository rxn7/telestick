package xyz.maciejniziolek.telestick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;

public final class GUI {
    public static final String INV_TITLE = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Telestick";
    public static final NamespacedKey GUI_PLAYER_HEAD_NAME_KEY = new NamespacedKey(Telestick.getPluginInstance(), "ts_player_head_name");

    public static void open(final Player player) {
        int playerCount = Bukkit.getOnlinePlayers().size();
        if(playerCount <= 1) {
            player.sendMessage(ChatColor.RED + LanguageUtil.getMessage("no_players_error", player.getLocale()));
            return;
        }

        int inventorySize = 9 * Math.round((playerCount -1) / 9);

        if(inventorySize < 9) inventorySize = 9;
        else if(inventorySize > 54) inventorySize = 54;

        Inventory inv = Bukkit.createInventory(player, inventorySize, INV_TITLE);

        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getUniqueId() == player.getUniqueId()) {
                continue;
            }

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta)playerHead.getItemMeta();
            meta.setOwnerProfile(p.getPlayerProfile());
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + p.getDisplayName());
            meta.setLore(Collections.singletonList(LanguageUtil.getMessage("teleport_to", player.getLocale()) + ChatColor.GOLD + " " + ChatColor.BOLD + p.getDisplayName()));
            meta.getPersistentDataContainer().set(GUI_PLAYER_HEAD_NAME_KEY, PersistentDataType.STRING, p.getDisplayName());
            playerHead.setItemMeta(meta);

            inv.addItem(playerHead);
        }

        player.openInventory(inv);
    }
}