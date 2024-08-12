package kr.yuna.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NoBlockExplodeListener implements Listener {



    @EventHandler
    public void onExplosion(BlockExplodeEvent event) {
        for (Block block : event.blockList()) {
            if (block.getType() == Material.RESPAWN_ANCHOR || block.getType() == Material.WHITE_BED|| block.getType() == Material.RED_BED|| block.getType() == Material.ORANGE_BED|| block.getType() == Material.YELLOW_BED|| block.getType() == Material.LIME_BED|| block.getType() == Material.GREEN_BED|| block.getType() == Material.PURPLE_BED|| block.getType() == Material.PINK_BED|| block.getType() == Material.BLACK_BED|| block.getType() == Material.GRAY_BED|| block.getType() == Material.LIGHT_GRAY_BED|| block.getType() == Material.BROWN_BED) {
                event.setCancelled(true);
                break;
            }
        }
    }
}
