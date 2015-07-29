package me.Cooltimmetje.PretparkCore.Managers;

import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 29-7-2015 at 14:57 by cooltimmetje.
 */
public class InventoryManager implements Listener{

    public static void setInventory(Player p){
        p.getInventory().clear();

        ItemStack skull = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, (byte)3, "&aJouw Profiel &3(Rechter Klik)", "&7&oTODO"); //TODO: MAKE LORE
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(p.getName());
        skull.setItemMeta(skullMeta);
        ItemUtils.createInventoryDisplay(p, skull, 1);

        ItemUtils.createInventoryDisplay(p, Material.MINECART, 1, (byte)0, "&aAttracties &3(Rechter Klik)", "&7&oTODO", 2); //TODO: MAKE LORE
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player p = event.getPlayer();
        if(!p.hasPermission("pretparkcore.bypassgm") && !p.isOp()){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        if(!p.hasPermission("pretparkcore.bypassgm") && !p.isOp()){
            event.setCancelled(true);
        }
    }

}
