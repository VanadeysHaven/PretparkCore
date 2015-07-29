package me.Cooltimmetje.PretparkCore.Events;

import me.Cooltimmetje.PretparkCore.Events.UserInterfaces.ProfileUI;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * This class has been created on 29-7-2015 at 15:55 by cooltimmetje.
 */
public class InventoryTriggers implements Listener {

    @EventHandler
    public void onRightClickItem(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getItem() != null){
                if (event.getItem().hasItemMeta()) {
                    switch (event.getItem().getType()) {
                        default:
                            break;
                        case SKULL_ITEM:
                            event.setCancelled(true);
                            ProfileUI.openUI(p);
                            break;
                        case MINECART:
                            event.setCancelled(true);
                            p.sendMessage(MiscUtils.color("&7SoonTM")); //TODO: MAKE GUI
                            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 50, 1);
                            break;
                    }
                }
            }
        }
    }

}
