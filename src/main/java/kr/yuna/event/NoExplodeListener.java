package kr.yuna.event;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NoExplodeListener implements Listener {
     @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.TNT|| event.getEntityType() == EntityType.TNT_MINECART || event.getEntityType() == EntityType.END_CRYSTAL || event.getEntityType() == EntityType.WITHER || event.getEntityType() == EntityType.CREEPER || event.getEntityType() == EntityType.FIREBALL) {
            event.setCancelled(true);
            event.blockList().clear();
        }
    }
}


