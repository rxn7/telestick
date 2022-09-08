package xyz.maciejniziolek.telestick;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;

public class GUIEventHandler implements Listener {
    @EventHandler
    public void onGuiClickEvent(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(GUI.INV_TITLE))
            return;

        if(e.getCurrentItem() == null)
            return;

        if(e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
            String playerName = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(GUI.GUI_PLAYER_HEAD_NAME_KEY, PersistentDataType.STRING);
            Player targetPlayer = Bukkit.getPlayer(playerName);

            if(targetPlayer != null)
                e.getWhoClicked().teleport(targetPlayer.getLocation());
        }

        e.setCancelled(true);
    }
}