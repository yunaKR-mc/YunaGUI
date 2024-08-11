package kr.yuna.guis.users.Turtorials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonGUI implements Listener {
    private  DungeonGUI dungeonGUI;
    private  final  JavaPlugin plugin;



    public  DungeonGUI(JavaPlugin plugin,DungeonGUI dungeonGUI) {

        this.plugin = plugin;
    }



    Inventory createInventory() {
        Inventory DunInv = Bukkit.createInventory(null,27, "던전 튜토리얼");




        return  DunInv;

    }

    public void openInventory(Player player) {
        Inventory inv = createInventory();
        player.openInventory(inv);
    }
}
